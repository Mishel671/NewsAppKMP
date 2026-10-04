# News App (Kotlin Multiplatform)
News app built with Kotlin Multiplatform, supporting Android and iOS. The app
follows the MVI architecture to ensure clean.

## Preview
<p>
<img src="./preview/ScreenRecordAndroid.gif" alt="Demo Android" width="360" height="808" >
<span>&shy;</span>
<img src="./preview/ScreenRecordiOS.gif" alt="Demo iOS" width="371" height="808">
</p>

## Build
- Generate a new key from [here](https://newsapi.org/docs/get-started)
- Add a new entry in `local.properties` file:

```properties
# local.properties (already gitignored)
sdk.dir=/Users/you/Library/Android/sdk

# Your secrets
API_KEY=your_api_key
```