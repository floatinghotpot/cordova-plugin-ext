
# Cordova Plugin Extension #

Extend the Cordova plugin base class with adapter interface.

Plugin written based on this interface, can also be used for Unity, Cocos2d-X, and other frameworks.

# Purpose of Project #

Re-use Cordova Plugins.

To use mobile device native functionalities and integrate 3rd-party SDKs, mobile developers are writting plugins for Cordova, Unity, Cocos2d-X, and other frameworks. 

Can they be reused? Yes, it's possible. 

Cordova plugin manager is bridging function call between javascript and native languages, it can be ported to bridge with C, C++, C#, then it can be reused for Unity, Cocos2d-X and other frameworks.

See related projects:
* [Cordova for Unity](https://github.com/floatinghotpot/cordova-for-unity)
* [Cordova for Cocos2d-X](https://github.com/floatinghotpot/cordova-for-cocos2dx)

# Android #

```javascript
public interface PluginAdapterDelegate {
	// context
	public Activity getActivity();
	public View getView();
	// send message from plugin to container on events
	public void fireEvent(String obj, String eventName, String jsonData);
	// send call result
	public void sendPluginResult(PluginResult result, CallbackContext context);
}

public class CordovaPluginExt extends CordovaPlugin implements PluginAdapterDelegate {
	protected PluginAdapterDelegate adapter = null;
}

// your plugin class
public class YourPluginClass extends CordovaPluginExt {
	// implement the method, call the API defined in PluginAdapterDelegate
	public boolean execute(String action, JSONArray inputs, CallbackContext callbackContext) throws JSONException;
}

```

# iOS #

```objective-c
@protocol PluginAdapterDelegate <NSObject>
	// context
- (UIView*) getView;
- (UIViewController*) getViewController;
	// send message from plugin to container on events
- (void) fireEvent:(NSString*)obj event:(NSString*)eventName withData:(NSString*)jsonStr;
	// send call result
- (void) sendPluginResult:(CDVPluginResult*)result to:(NSString*)callbackId;
@end

@interface CDVPluginExt : CDVPlugin <PluginAdapterDelegate>
@property(nonatomic, retain) id<PluginAdapterDelegate> adapter;
@end

// your plugin class
@interface YourPluginClass : CDVPluginExt
	// implement the method, call the API defined in PluginAdapterDelegate
- (void) your_method:(CDVInvokedUrlCommand *)command;
@end

```

# Windows #

Not implemented yet.

# Credit #

This project is created by Raymond Xie.

If you are interested in this project, welcome to join and contribute.

