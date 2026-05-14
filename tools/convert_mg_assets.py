from pathlib import Path
import shutil


PNG_SIGNATURE = bytes([0x89, 0x50, 0x4E, 0x47])


def convert_file(src: Path, dst: Path) -> bool:
    data = src.read_bytes()
    if len(data) < 8:
        return False

    png_size = int.from_bytes(data[:4], "big")
    if png_size <= 8 or png_size > len(data):
        return False

    png = PNG_SIGNATURE + data[4:png_size]
    if png[4:8] != b"\r\n\x1a\n":
        return False

    dst.parent.mkdir(parents=True, exist_ok=True)
    dst.write_bytes(png)
    return True


def main() -> None:
    root = Path(__file__).resolve().parents[1]
    source = root / "source_code"
    resources = root / "UnityPort" / "Assets" / "Resources" / "Loan12"
    audio_out = root / "UnityPort" / "Assets" / "StreamingAssets" / "audio"

    converted = 0
    skipped = 0
    for src in source.rglob("*.mg"):
        rel = src.relative_to(source).with_suffix(".png")
        if convert_file(src, resources / rel):
            converted += 1
        else:
            skipped += 1

    audio_out.mkdir(parents=True, exist_ok=True)
    for src in (source / "audio").glob("*"):
        if src.is_file():
            shutil.copy2(src, audio_out / src.name)

    print(f"Converted {converted} .mg files to PNG.")
    if skipped:
        print(f"Skipped {skipped} files that did not match the .mg PNG wrapper.")
    print(f"Copied audio to {audio_out}")


if __name__ == "__main__":
    main()
