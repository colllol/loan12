# Loan 12 Su Quan Unity Port

This folder is a Unity port foundation for the original Java ME game.

## What is included

- Restored PNG resources under `Assets/Resources/Loan12`.
- Original AMR/MID audio copied to `Assets/StreamingAssets/audio`.
- A 240x320 J2ME-style runtime canvas with centered scaling for modern iOS screens.
- Splash screens, main menu shell, and a board placeholder using original assets.
- An editor menu item: `Loan12/Apply iOS Build Settings`.

## How to run

1. Open `UnityPort` with Unity 2022.3 LTS or newer.
2. Open or create any empty scene.
3. Press Play. `Loan12Bootstrap` creates the camera and game runtime automatically.
4. For local iOS export, run `Loan12/Build iOS Xcode Project`.
5. For `.ipa` without macOS, use Unity Build Automation. See `CLOUD_BUILD_IOS.md`.

## Porting notes

The original source is decompiled and heavily obfuscated Java ME code. The next migration step is to translate the classes behind `SQMidlet -> cz -> bv/y/g` into named Unity systems:

- screen stack and modal dialogs
- board/combat rules
- inventory/shop/progression
- save data
- audio conversion from AMR/MID to AAC/WAV/OGG

The `.mg` asset format is a PNG wrapper: 4 big-endian bytes for the PNG length, followed by PNG data with the first 4 PNG signature bytes removed. Run `python ../tools/convert_mg_assets.py` again after changing source assets.
