# Image generation utilities

Reusable image-generation helpers powered by Google’s Nano Banana Pro image
model (`gemini-3-pro-image-preview`). Prompts are supplied when the command is
run; no project-specific prompt is stored in this repository.

## Setup

Run these commands from `image-gen-utils` in PowerShell:

```powershell
uv venv
uv sync
$env:GEMINI_API_KEY = "paste-your-gemini-api-key-here"
```

The API key is read only from the current shell session.

## Generate an image

```powershell
uv run python generate_image.py `
  --prompt "Describe the image you want Nano Banana Pro to create" `
  --output output/example.png
```

For a longer prompt, keep it in a local text file:

```powershell
uv run python generate_image.py `
  --prompt-file .\prompt.txt `
  --reference .\reference.png `
  --image-size 4K `
  --output output/result.png
```

Use `--reference` more than once to provide multiple reference images. The
default model is Nano Banana Pro, and `--model` can override it if Google
changes the preview model name. Generated images belong in `output/` and are
ignored by Git.

## Project metadata

```powershell
uv run python -m compileall -q .
```
