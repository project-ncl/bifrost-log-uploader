# bifrost-log-uploader
Log uploader for Bifrost

```
BifrostLogUploader logUploader = new BifrostLogUploader("http://localhost:8080", 5, 1, () -> "token");

LogMetadata logMetadata = LogMetadata.builder()
    .endTime(OffsetDateTime.now())
    .loggerName("myLogger")
    .tag("myTag")
    .processContext("build-AAAAAAA123456")
    .processContextVariant("0")
    .tmp("false")
    .requestContext("123456789741")
    .build();

String logOutput = "Everything went swimmingly";

try {
    logUploader.uploadString(logOutput, logMetadata);
} catch(BifrostUploadException up) {
    throw up;
}
```
