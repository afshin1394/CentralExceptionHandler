# Concentrated_Exception_Handler

This library is an easy way to control and track your android application crashes. 
 
 
## Quick Setup ##


### Gradle ###
Add these dependancies to your biuld.gradle(appLevel) file:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  




```

Also add these dependancies to your biuld.gradle(appLevel) file:
```
dependencies {
	        implementation 'com.github.afshin1394:central_exception_handler:$latestVersion'
	}

```
latestVersion:
[![](https://jitpack.io/v/afshin1394/central_exception_handler.svg)](https://jitpack.io/#afshin1394/central_exception_handler)



### Maven ###


```
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

```
<dependency>
	    <groupId>com.github.afshin1394</groupId>
	    <artifactId>central_exception_handler</artifactId>
	    <version>$latestVersion</version>
	</dependency>
```  


lastestVersion:
[![](https://jitpack.io/v/afshin1394/central_exception_handler.svg)](https://jitpack.io/#afshin1394/central_exception_handler)

## Quick Start ##

To use central exeption handling features you just need to extend your Application.class from ExceptionHandlerApplication.class 
and implement it's method:
```
public class YourApplication extends ExceptionHandlerApplication {


    @Override
    public void onCreate() {

        super.onCreate();

    }

    @Override
    protected void exceptionCached(Crash crash, List<Activity> activities) {

    }
```


In exceptionCached method you have access to two parameters one of them is the details of the crash that has happened in your application 
and the other one is the list of your activities that has been added to your activity stack list till the crash occurs:

```
  @Override
    protected void exceptionCached(Crash crash, List<Activity> activities) {


        /**available details**/
        String crashClass = crash.getCrashClass();
        String crashMethod = crash.getCrashMethod();
        String crashLine = crash.getCrashLine();
        String crashStacktrace = crash.getCrashStackTrace();
        String crashFile = crash.getCrashFile();
        String errorMessage = crash.getErrorMessage();

        for (Activity activity : activities) {
            activity.finish();
        }

    }
    
```

In addition you can set default values for your crash details:

```
public class BaseApplication extends ExceptionHandlerApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        YourApplication.defaultMethodName = getString(R.string.invalid);
        YourApplication.defaultClassName = getString(R.string.invalid);
        YourApplication.defaultFileName = getString(R.string.invalid);
        YourApplication.defaultLineNumber = getString(R.string.invalid);
        YourApplication.defaultLineNumber = getString(R.string.unAvailable);
        YourApplication.defaultErrorStackTrace = getString(R.string.unAvailable);
    }
    
```
    
This library also supports androidx multidex library.    









      
      

