# Build iOS IPA With Unity Build Automation

Use this when you do not have macOS. Unity Build Automation can build and sign iOS in the cloud and return an `.ipa`.

## Project settings

- Unity version: `2022.3.62f3`
- Project path in repository: `UnityPort`
- Target platform: `iOS`
- Bundle identifier: `com.botnosense.loan12suquan`
- Minimum iOS version: `12.0`
- Build scene: `Assets/Scenes/Boot.unity`
- Optional build method: `Loan12IOSBuildSettings.BuildIosXcodeProject`

## Required Apple files

You still need Apple signing credentials:

- Apple Developer account
- iOS Distribution certificate exported as `.p12`
- Provisioning profile `.mobileprovision`
- The certificate password
- Apple Team ID

For TestFlight/App Store, create an App Store distribution provisioning profile. For direct device testing outside TestFlight, use an Ad Hoc profile that includes your device UDIDs.

## Unity Dashboard steps

1. Push this repository to GitHub.
2. Open Unity Dashboard.
3. Go to `DevOps > Build Automation`.
4. Connect the GitHub repository.
5. Create a build target:
   - Platform: `iOS`
   - Unity version: `2022.3.62f3`
   - Project path: `UnityPort`
   - Branch: your active branch
6. Add signing credentials:
   - Upload `.p12`
   - Upload `.mobileprovision`
   - Enter the certificate password and Team ID
7. Start a clean build.
8. Download the generated `.ipa` from the build result.

## Notes

- Windows cannot produce a signed `.ipa` locally because Apple signing requires Xcode/macOS tooling.
- Unity Build Automation handles the macOS/Xcode side in the cloud.
- If signing fails, regenerate the provisioning profile after creating the certificate, then upload the new profile to Unity Build Automation.
