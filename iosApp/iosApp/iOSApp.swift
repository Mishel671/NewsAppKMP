import SwiftUI

import Shared

@main
struct iOSApp: App {
    init() {
        KoinInitIosKt.InitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}