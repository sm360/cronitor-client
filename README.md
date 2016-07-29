# cronitor-client

## What is it ?
cronitor-client is a simple Java library designed to use the awesome Cronitor API in order to monitor routines of your Java projects.

## How to install it on your Maven project ?
Simply add this line in your pom.xml file :
```
<dependency>
    <groupId>ca.sm360.cronitor</groupId>
    <artifactId>cronitor-client</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Usage with Spring

Declare a new bean in your Spring configuration :
```
// if you are using the Auth Key of your Cronitor account
<bean id="cronitorClient" class="ca.sm360.cronitor.client.CronitorClient">
     <constructor-arg index="0" value="authKey"/>
</bean>

// otherwise
<bean id="cronitorClient" class="ca.sm360.cronitor.client.CronitorClient" />
```

Then simply inject this bean in the class containing the routine to monitor :
```
@Resource
private CronitorClient cronitorClient;
```

### Usage without Spring
Simply declare a new CronitorClient instance in the class containing the routine to monitor :
```
CronitorClient cronitorClient = new CronitorClient();
```

### Examples

### ping /run on a monitor
```
    try {
        // ping the /run URL on your monitor
        cronitorClient.run(monitorCode);
    } catch (Exception e) {
        // handle the raised exception the way you want
    }
```
### ping /complete on a monitor
```
    try {
        // ping the /complete URL on your monitor
        cronitorClient.complete(monitorCode);
    } catch (Exception e) {
        // handle the raised exception the way you want
    }
```
### ping /fail on a monitor
```
    try {
        // ping the /fail URL on your monitor with no extra message
        cronitorClient.fail(monitorCode);
        
        // ping the /fail URL on your monitor with an extra message
        cronitorClient.fail(monitorCode, message);
    } catch (Exception e) {
        // handle the raised exception the way you want
    }
```
### pause a monitor
```
    try {
        // pause a monitor for a number of hours
        cronitorClient.pause(monitorCode, numberOfHours);
    } catch (Exception e) {
        // handle the raised exception the way you want
    }
```
### unpause a monitor
```
    try {
        // unpause a monitor
        cronitorClient.unpause(monitorCode);
    } catch (Exception e) {
        // handle the raised exception the way you want
    }
```

## How to contact us ?

If you want to share an idea, report an issue or just say hello, do not hesitate to write to us at github@360.agency 

Have a great day!
