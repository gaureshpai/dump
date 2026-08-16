"""Generate an image with Google's Nano Banana Pro model.

The prompt is always supplied at runtime. This keeps the utility reusable for
any image-generation task instead of coupling it to one asset or project.
"""

from __future__ import annotations

import argparse
import os
from datetime import datetime
from pathlib import Path

from google import genai
from google.genai import types
from PIL import Image


DEFAULT_MODEL = "gemini-3-pro-image-preview"
DEFAULT_OUTPUT_DIR = Path(__file__).resolve().parent / "output"


def image_part(path: Path) -> types.Part:
    if not path.is_file():
        raise FileNotFoundError(f"Reference image not found: {path}")
    mime_types = {".jpg": "image/jpeg", ".jpeg": "image/jpeg", ".webp": "image/webp"}
    mime_type = mime_types.get(path.suffix.lower(), "image/png")
    return types.Part.from_bytes(data=path.read_bytes(), mime_type=mime_type)


def response_image(response: types.GenerateContentResponse) -> bytes:
    for candidate in response.candidates or []:
        for part in candidate.content.parts or []:
            if part.inline_data and part.inline_data.data:
                return part.inline_data.data
    raise RuntimeError("Nano Banana Pro returned no image data.")


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser(description=__doc__)
    prompt = parser.add_mutually_exclusive_group(required=True)
    prompt.add_argument("--prompt", help="Image-generation instructions.")
    prompt.add_argument("--prompt-file", type=Path, help="UTF-8 file containing the prompt.")
    parser.add_argument("--reference", type=Path, action="append", default=[], help="Reference image; repeat for multiple images.")
    parser.add_argument("--output", type=Path, help="Exact output path. Defaults to output/image-<timestamp>.png.")
    parser.add_argument("--output-dir", type=Path, default=DEFAULT_OUTPUT_DIR)
    parser.add_argument("--model", default=DEFAULT_MODEL)
    parser.add_argument("--aspect-ratio", default="16:9")
    parser.add_argument("--image-size", choices=("1K", "2K", "4K"), default="4K")
    return parser.parse_args()


def main() -> None:
    args = parse_args()
    api_key = os.environ.get("GEMINI_API_KEY")
    if not api_key:
        raise SystemExit("Set GEMINI_API_KEY before running this script.")
    if args.prompt_file:
        prompt = args.prompt_file.read_text(encoding="utf-8")
    else:
        prompt = args.prompt
    if not prompt or not prompt.strip():
        raise SystemExit("The prompt must not be empty.")

    contents: list[types.Part | str] = [image_part(path) for path in args.reference]
    contents.append(prompt)
    response = genai.Client(api_key=api_key).models.generate_content(
        model=args.model,
        contents=contents,
        config=types.GenerateContentConfig(
            response_modalities=["IMAGE"],
            image_config=types.ImageConfig(aspect_ratio=args.aspect_ratio, image_size=args.image_size),
        ),
    )

    timestamp = datetime.now().astimezone().strftime("%Y%m%d-%H%M%S")
    output_path = args.output or args.output_dir / f"image-{timestamp}.png"
    output_path.parent.mkdir(parents=True, exist_ok=True)
    output_path.write_bytes(response_image(response))
    with Image.open(output_path) as image:
        print(f"Saved {output_path}: {image.width}x{image.height}")


if __name__ == "__main__":
    main()
