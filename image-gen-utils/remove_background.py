"""Make edge-connected near-white pixels transparent in a generated image."""

from __future__ import annotations

import argparse
from collections import deque
from pathlib import Path

from PIL import Image


def is_background(pixel: tuple[int, int, int], threshold: int, tolerance: int) -> bool:
    return min(pixel) >= threshold and max(pixel) - min(pixel) <= tolerance


def main() -> None:
    parser = argparse.ArgumentParser(description=__doc__)
    parser.add_argument("input", type=Path)
    parser.add_argument("output", type=Path)
    parser.add_argument("--threshold", type=int, default=245)
    parser.add_argument("--color-tolerance", type=int, default=12)
    args = parser.parse_args()
    with Image.open(args.input) as source:
        image = source.convert("RGBA")
        width, height = image.size
        pixels = image.load()
        queue: deque[tuple[int, int]] = deque()
        visited: set[tuple[int, int]] = set()

        def visit(x: int, y: int) -> None:
            if (x, y) not in visited and is_background(pixels[x, y][:3], args.threshold, args.color_tolerance):
                visited.add((x, y))
                queue.append((x, y))

        for x in range(width):
            visit(x, 0)
            visit(x, height - 1)
        for y in range(height):
            visit(0, y)
            visit(width - 1, y)
        while queue:
            x, y = queue.popleft()
            red, green, blue, _ = pixels[x, y]
            pixels[x, y] = (red, green, blue, 0)
            for next_x, next_y in ((x - 1, y), (x + 1, y), (x, y - 1), (x, y + 1)):
                if 0 <= next_x < width and 0 <= next_y < height:
                    visit(next_x, next_y)
        args.output.parent.mkdir(parents=True, exist_ok=True)
        image.save(args.output, format="PNG", optimize=False, compress_level=6)
    print(f"Saved {args.output} as RGBA {width}x{height}")


if __name__ == "__main__":
    main()
