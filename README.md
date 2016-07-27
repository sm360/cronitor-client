# cronitor-client

## What is it ?

## How to install it on your Maven project ?
Simply add this line in your pom.xml file :
```
<dependency>
    <groupId>com.sm360.cronitor</groupId>
    <artifactId>cronitor-client</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Usage with Spring

And declare a new bean in your Spring configuration :
```
// If you are using an auth key for your api calls
<bean id="cronitorClient" class="com.sm360.cronitor.client.CronitorClient">
     <constructor-arg index="0" value="authKey"/>
</bean>

// Otherwise
<bean id="cronitorClient" class="com.sm360.cronitor.client.CronitorClient" />
```

Simply inject this bean in the class containing the routine to monitor :
```
@Resource
private CronitorClient cronitorClient;
```
### Examples

### ping /run for a monitor
```
    // Initialize cronitorClient
    CronitorClient cronitorClient = new CronitorClient();
    
    try {
        //Ping /run of your monitor
        cronitorClient.run(monitorCode);
    } catch (Exception e) {
    
    }
```
### ping /complete for a monitor
```
    // Initialize cronitorClient
    CronitorClient cronitorClient = new CronitorClient();
    
    try {
        //Ping /complete of your monitor
        cronitorClient.complete(monitorCode);
    } catch (Exception e) {
    
    }
```
### ping /fail for a monitor
```
    // Initialize cronitorClient
    CronitorClient cronitorClient = new CronitorClient();
    
    try {
        //Ping /fail of your monitor with no message
        cronitorClient.fail(monitorCode);
        //Ping /fail of your monitor with a message
        //cronitorClient.fail(monitorCode, message);
    } catch (Exception e) {
    
    }
```
### pause a monitor
```
    // Initialize cronitorClient
    CronitorClient cronitorClient = new CronitorClient();
    
    try {
        //Pause a monitor
        cronitorClient.pause(monitorCode, timeoutInHouirs);
    } catch (Exception e) {
    
    }
```
### unpause a monitor
```
    // Initialize cronitorClient
    CronitorClient cronitorClient = new CronitorClient();
    
    try {
        //Unpause a monitor
        cronitorClient.unpause(monitorCode);
    } catch (Exception e) {
    
    }
```

## How to contact us ?

If you want to share an idea, report an issue or just say hello, do not hesitate to write to us at github@360.agency 

Have a great day!
