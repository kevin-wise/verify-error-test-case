# Test case for VerifyError

Key classes:

* IntegrationTestSupport - base class for initializing server mocks (in a lib)
* ApiTestSupport - extends IntegrationTestSupport, adds project-specific setup
* AdminControllerTest - extends ApiTestSupport. Written in Groovy, sometimes fails and sometimes
  succeeds
* AdminControllerJavaTest - extends ApiTestSupport. Written in Java, always works as expected
  despite being functionally identical to AdminControllerTest

Always succeeds:
```commandline
./gradlew :api:test --tests "com.testcase.verifyerror.AdminControllerJavaTest" --stacktrace
```

Sometimes succeeds (see below):
```commandline
./gradlew :api:test --tests "com.testcase.verifyerror.AdminControllerTest" --stacktrace
```

Ways to make it work as expected:

* comment out `isDatastoreIndexCheckEnabled` in IntegrationTestSupport
* override `isDatastoreIndexCheckEnabled` in ApiTestSupport

So it must be something about that method, or that in combination with something else

When it fails, I get something like this:

```text
> Task :api:compileJava UP-TO-DATE
> Task :api:compileGroovy NO-SOURCE
> Task :api:processResources UP-TO-DATE
> Task :api:classes UP-TO-DATE
> Task :lib:compileJava UP-TO-DATE
> Task :lib:compileGroovy NO-SOURCE
> Task :lib:processResources NO-SOURCE
> Task :lib:classes UP-TO-DATE
> Task :lib:jar UP-TO-DATE
> Task :api:compileTestJava
warning: unknown enum constant When.MAYBE
  reason: class file for javax.annotation.meta.When not found
Note: Creating bean classes for 1 type elements
1 warning
> Task :api:compileTestGroovy
Picked up _JAVA_OPTIONS: -Dfile.encoding=UTF-8

> Task :api:processTestResources UP-TO-DATE
> Task :api:testClasses
> Task :api:test
Picked up _JAVA_OPTIONS: -Dfile.encoding=UTF-8

SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#StaticLoggerBinder for further details.

Error loading bean [com.testcase.verifyerror.AdminControllerTest]: Bad type on operand stack
Exception Details:
  Location:
    com/testcase/verifyerror/$AdminControllerTest$Definition$Exec.dispatch(ILjava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object; @94: invokestatic
  Reason:
    Type 'java/lang/Object' (current frame, stack[0]) is not assignable to integer
  Current Frame:
    bci: @94
    flags: { }
    locals: { 'com/testcase/verifyerror/$AdminControllerTest$Definition$Exec', integer, 'java/lang/Object', '[Ljava/lang/Object;' }
    stack: { 'java/lang/Object' }
  Bytecode:
    0000000: 1baa 0000 0000 0090 0000 0000 0000 0008
    0000010: 0000 0033 0000 003d 0000 0047 0000 0051
    0000020: 0000 0061 0000 006a 0000 0073 0000 007c
    0000030: 0000 0086 2c59 c000 0db6 0090 01b0 2c59
    0000040: c000 0db6 0092 01b0 2c59 c000 0db6 0094
    0000050: 01b0 2c2a 06b6 0098 b200 9eb8 00a4 b800
    0000060: aab0 2c59 c000 5eb6 00ad b02c 59c0 005e
    0000070: b600 b0b0 2c59 c000 5eb6 00b3 b02c 59c0
    0000080: 007f b600 b501 b02c 59c0 007f b600 b701
    0000090: b02a 1bb6 00bb bf                      
  Stackmap Table:
    same_frame(@52)
    same_frame(@62)
    same_frame(@72)
    same_frame(@82)
    same_frame(@98)
    same_frame(@107)
    same_frame(@116)
    same_frame(@125)
    same_frame(@135)
    same_frame(@145)

io.micronaut.context.exceptions.BeanContextException: Error loading bean [com.testcase.verifyerror.AdminControllerTest]: Bad type on operand stack
Exception Details:
  Location:
    com/testcase/verifyerror/$AdminControllerTest$Definition$Exec.dispatch(ILjava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object; @94: invokestatic
  Reason:
    Type 'java/lang/Object' (current frame, stack[0]) is not assignable to integer
  Current Frame:
    bci: @94
    flags: { }
    locals: { 'com/testcase/verifyerror/$AdminControllerTest$Definition$Exec', integer, 'java/lang/Object', '[Ljava/lang/Object;' }
    stack: { 'java/lang/Object' }
  Bytecode:
    0000000: 1baa 0000 0000 0090 0000 0000 0000 0008
    0000010: 0000 0033 0000 003d 0000 0047 0000 0051
    0000020: 0000 0061 0000 006a 0000 0073 0000 007c
    0000030: 0000 0086 2c59 c000 0db6 0090 01b0 2c59
    0000040: c000 0db6 0092 01b0 2c59 c000 0db6 0094
    0000050: 01b0 2c2a 06b6 0098 b200 9eb8 00a4 b800
    0000060: aab0 2c59 c000 5eb6 00ad b02c 59c0 005e
    0000070: b600 b0b0 2c59 c000 5eb6 00b3 b02c 59c0
    0000080: 007f b600 b501 b02c 59c0 007f b600 b701
    0000090: b02a 1bb6 00bb bf                      
  Stackmap Table:
    same_frame(@52)
    same_frame(@62)
    same_frame(@72)
    same_frame(@82)
    same_frame(@98)
    same_frame(@107)
    same_frame(@116)
    same_frame(@125)
    same_frame(@135)
    same_frame(@145)

	at app//io.micronaut.context.DefaultBeanContext.findBeanCandidates(DefaultBeanContext.java:2115)
	at app//io.micronaut.context.DefaultApplicationContext.findBeanCandidates(DefaultApplicationContext.java:248)
	at app//io.micronaut.context.DefaultBeanContext.findBeanCandidatesInternal(DefaultBeanContext.java:3475)
	at app//io.micronaut.context.DefaultBeanContext.findBeanDefinition(DefaultBeanContext.java:785)
	at app//io.micronaut.context.DefaultBeanContext.findBeanDefinition(DefaultBeanContext.java:803)
	at app//io.micronaut.context.BeanDefinitionRegistry.findBeanDefinition(BeanDefinitionRegistry.java:533)
	at app//io.micronaut.test.extensions.AbstractMicronautExtension.beforeClass(AbstractMicronautExtension.java:240)
	at app//io.micronaut.test.extensions.junit5.MicronautJunit5Extension.beforeAll(MicronautJunit5Extension.java:62)
	at app//org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.lambda$invokeBeforeAllCallbacks$10(ClassBasedTestDescriptor.java:381)
	at app//org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at app//org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.invokeBeforeAllCallbacks(ClassBasedTestDescriptor.java:381)
	at app//org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:205)
	at app//org.junit.jupiter.engine.descriptor.ClassBasedTestDescriptor.before(ClassBasedTestDescriptor.java:80)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:148)
	at app//org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at app//org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at app//org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at java.base@11.0.12/java.util.ArrayList.forEach(ArrayList.java:1541)
	at app//org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.invokeAll(SameThreadHierarchicalTestExecutorService.java:41)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$6(NodeTestTask.java:155)
	at app//org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$8(NodeTestTask.java:141)
	at app//org.junit.platform.engine.support.hierarchical.Node.around(Node.java:137)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.lambda$executeRecursively$9(NodeTestTask.java:139)
	at app//org.junit.platform.engine.support.hierarchical.ThrowableCollector.execute(ThrowableCollector.java:73)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.executeRecursively(NodeTestTask.java:138)
	at app//org.junit.platform.engine.support.hierarchical.NodeTestTask.execute(NodeTestTask.java:95)
	at app//org.junit.platform.engine.support.hierarchical.SameThreadHierarchicalTestExecutorService.submit(SameThreadHierarchicalTestExecutorService.java:35)
	at app//org.junit.platform.engine.support.hierarchical.HierarchicalTestExecutor.execute(HierarchicalTestExecutor.java:57)
	at app//org.junit.platform.engine.support.hierarchical.HierarchicalTestEngine.execute(HierarchicalTestEngine.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:108)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:88)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.lambda$execute$0(EngineExecutionOrchestrator.java:54)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.withInterceptedStreams(EngineExecutionOrchestrator.java:67)
	at org.junit.platform.launcher.core.EngineExecutionOrchestrator.execute(EngineExecutionOrchestrator.java:52)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:96)
	at org.junit.platform.launcher.core.DefaultLauncher.execute(DefaultLauncher.java:75)
	at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor$CollectAllTestClassesExecutor.processAllTestClasses(JUnitPlatformTestClassProcessor.java:99)
	at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor$CollectAllTestClassesExecutor.access$000(JUnitPlatformTestClassProcessor.java:79)
	at org.gradle.api.internal.tasks.testing.junitplatform.JUnitPlatformTestClassProcessor.stop(JUnitPlatformTestClassProcessor.java:75)
	at org.gradle.api.internal.tasks.testing.SuiteTestClassProcessor.stop(SuiteTestClassProcessor.java:61)
	at java.base@11.0.12/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base@11.0.12/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at java.base@11.0.12/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base@11.0.12/java.lang.reflect.Method.invoke(Method.java:566)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:36)
	at org.gradle.internal.dispatch.ReflectionDispatch.dispatch(ReflectionDispatch.java:24)
	at org.gradle.internal.dispatch.ContextClassLoaderDispatch.dispatch(ContextClassLoaderDispatch.java:33)
	at org.gradle.internal.dispatch.ProxyDispatchAdapter$DispatchingInvocationHandler.invoke(ProxyDispatchAdapter.java:94)
	at com.sun.proxy.$Proxy2.stop(Unknown Source)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker$3.run(TestWorker.java:193)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.executeAndMaintainThreadName(TestWorker.java:129)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:100)
	at org.gradle.api.internal.tasks.testing.worker.TestWorker.execute(TestWorker.java:60)
	at org.gradle.process.internal.worker.child.ActionExecutionWorker.execute(ActionExecutionWorker.java:56)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:133)
	at org.gradle.process.internal.worker.child.SystemApplicationClassLoaderWorker.call(SystemApplicationClassLoaderWorker.java:71)
	at app//worker.org.gradle.process.internal.worker.GradleWorkerMain.run(GradleWorkerMain.java:69)
	at app//worker.org.gradle.process.internal.worker.GradleWorkerMain.main(GradleWorkerMain.java:74)
Caused by: java.lang.VerifyError: Bad type on operand stack
Exception Details:
  Location:
    com/testcase/verifyerror/$AdminControllerTest$Definition$Exec.dispatch(ILjava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object; @94: invokestatic
  Reason:
    Type 'java/lang/Object' (current frame, stack[0]) is not assignable to integer
  Current Frame:
    bci: @94
    flags: { }
    locals: { 'com/testcase/verifyerror/$AdminControllerTest$Definition$Exec', integer, 'java/lang/Object', '[Ljava/lang/Object;' }
    stack: { 'java/lang/Object' }
  Bytecode:
    0000000: 1baa 0000 0000 0090 0000 0000 0000 0008
    0000010: 0000 0033 0000 003d 0000 0047 0000 0051
    0000020: 0000 0061 0000 006a 0000 0073 0000 007c
    0000030: 0000 0086 2c59 c000 0db6 0090 01b0 2c59
    0000040: c000 0db6 0092 01b0 2c59 c000 0db6 0094
    0000050: 01b0 2c2a 06b6 0098 b200 9eb8 00a4 b800
    0000060: aab0 2c59 c000 5eb6 00ad b02c 59c0 005e
    0000070: b600 b0b0 2c59 c000 5eb6 00b3 b02c 59c0
    0000080: 007f b600 b501 b02c 59c0 007f b600 b701
    0000090: b02a 1bb6 00bb bf                      
  Stackmap Table:
    same_frame(@52)
    same_frame(@62)
    same_frame(@72)
    same_frame(@82)
    same_frame(@98)
    same_frame(@107)
    same_frame(@116)
    same_frame(@125)
    same_frame(@135)
    same_frame(@145)

	at com.testcase.verifyerror.$AdminControllerTest$Definition.<init>(Unknown Source)
	at com.testcase.verifyerror.$AdminControllerTest$Definition.<init>(Unknown Source)
	at com.testcase.verifyerror.$AdminControllerTest$Definition$Reference.load(Unknown Source)
	at io.micronaut.context.AbstractInitializableBeanDefinitionReference.load(AbstractInitializableBeanDefinitionReference.java:145)
	at io.micronaut.context.DefaultBeanContext.findBeanCandidates(DefaultBeanContext.java:2113)
	... 62 more


AdminControllerTest > initializationError FAILED
    io.micronaut.context.exceptions.BeanContextException at DefaultBeanContext.java:2115
        Caused by: java.lang.VerifyError at null:-1
1 test completed, 1 failed
> Task :api:test FAILED
FAILURE: Build failed with an exception.
* What went wrong:
Execution failed for task ':api:test'.
> There were failing tests. See the report at: file:///C:/dev/src/VerifyError-test/api/build/reports/tests/test/index.html
* Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
* Get more help at https://help.gradle.org
BUILD FAILED in 15s
8 actionable tasks: 3 executed, 5 up-to-date
```

I had my co-worker try this and he gets a lockfile error that I don't see. If that happens, you
can either delete the lockfiles or regenerate them with `./gradlew generateLock --write-locks`.
