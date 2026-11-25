# Java Examples

These instructions apply to the Java examples under this directory.

- Create code only when the user explicitly asks for code to be created. Discussion, questions, research, and requests for instructions or documentation do not authorize creating code.
- Always ask the user for confirmation before making any changes under this directory, including creating, editing, renaming, or deleting files. Describe the proposed changes and wait for confirmation before proceeding.
- When adapting existing code, preserve existing copyright and license notices and identify the original source or author when known. Include license references only when accurate.
- Favor explicit objects, named methods, and ordinary Java control flow so construction and execution are easy to follow.
- When explaining proposed Java changes, show the actual code needed when it is reasonably short, rather than describing edits only in prose. Include required imports, fields, and method changes, and specify the affected file and where each change belongs. For longer changes, show the relevant sections and clearly describe any omitted code. Showing code in chat does not authorize modifying files.
- When porting a book example, preserve its intended behavior and distinguish simulated handlers from real services or model calls.
- Keep credentials out of source code and documentation. Use environment variables or ignored local configuration.
- Do not make live model API calls unless the user explicitly authorizes them.
- Document setup, execution, and any validation actually performed. Do not claim that examples were tested when they were not.

## Java application logging

This section and the two logging sections that follow apply only to Java examples under `java/`, including the Maven project under `java/langchain4j/`. They do not require SLF4J, POJO conversion, or Java HTTP logging in Python, notebooks, or other languages. Credential protection applies throughout the repository.

- Every Java class must have logging using SLF4J, with a logger associated with that class. Every method, including constructors, getters, setters, and private helpers, must log its inputs and its return value or completion. Log exceptional exits as well.
- Log major pieces of logic appropriate to the pattern: request classification, routing decisions, handler selection, tool calls, parallel tasks, reflection iterations, AI stages, validation outcomes, and output generation. Include safe correlation identifiers so an example request can be followed through the workflow.
- Use TRACE for routine accessor/constructor logging, DEBUG for method inputs and outputs and implementation details, INFO for major workflow milestones, WARN for recoverable problems, and ERROR for failures. Use parameterized messages rather than string concatenation.
- Configure the logging threshold as TRACE when the application is launched with JVM debugging enabled (for example, Eclipse's Debug As with JDWP), and DEBUG otherwise. Configure this before application logging begins. This refers to debugger-enabled launch configuration, not to whether a debugger happens to be attached at a particular instant.
- Use SLF4J backed by Log4j 2, with output to the console. The LangChain4j project's configuration belongs in `src/main/resources/log4j2.xml`; do not add file logging unless requested.
- For collections and large objects, log safe identifiers, counts, status values, and concise summaries instead of dumping entire objects, documents, prompts, or AI responses, except for the narrowly authorized demo logging below. A void method should log completion rather than a return value.
- Never log passwords, API keys, Authorization headers, authentication or CSRF tokens, session cookies, SSNs, or sensitive personal information. Logging requirements do not authorize disclosure of sensitive values. Sanitize untrusted text to prevent forged log entries.
- Do not swallow exceptions for logging or change application behavior solely to produce log entries. Avoid repeatedly logging the same exception at every layer.
- Apply these requirements when creating or modifying Java code. Adding logging retroactively to other existing classes requires approval under the change-control instructions.

## Java demos: OpenAI request and response logging

- For OpenAI calls using non-sensitive sample or fictional demo data, log the full prompt text sent (system/developer instructions and user messages, including generated JSON-schema/output-format instructions when present) in a separate DEBUG entry, followed by the returned response JSON in its own DEBUG entry. This applies to direct SDK calls and calls through LangChain4j.
- Log response content before POJO conversion and application validation so a rejected response remains inspectable. Do not substitute a POJO summary for the actual response. If a call fails before a response arrives, log the failure type; never fabricate a response entry.
- Capture the actual instructions and response exposed by the client integration. Do not reconstruct missing framework-generated content and present it as the actual request or response. If the integration does not expose the required content safely, explain the limitation before proposing implementation changes.
- This demo exception permits prompt/input and model-output content logging only for non-sensitive sample or fictional data. Real personal, confidential, or proprietary content must follow the summary/redaction policy above. It never authorizes logging credentials, API keys, Authorization headers, cookies, tokens, or transport request envelopes.
- Keep prompt text and response JSON separate: no combined request/response object. Log through SLF4J so entries use the configured console output. Escape embedded CR/LF in untrusted content to prevent forged log entries while preserving inspectable text.
- Logging instructions do not authorize live model API calls; those still require explicit user approval.

## Java applications: external HTTP transport metadata logging

- Every external HTTP client must log each outbound attempt and its response, including SDK retries, using a shared safe HTTP metadata logger within the owning Java project. If none exists, propose its implementation within the requested scope rather than assuming one already exists.
- Log a local correlation ID, provider, HTTP method, destination scheme/host/port and path (omit user info, query values and fragments), safe request headers, response status, safe response headers, and elapsed milliseconds to response headers. Log network/timeout failure types and elapsed time when no response arrives.
- These transport entries must never contain request/response payloads. Keep separately authorized OpenAI prompt/JSON logs distinct from HTTP metadata logs.
- Header logging must use an explicit allowlist: content metadata, request IDs, retry/timeout metadata, and rate-limit headers. Never dump all headers. Exclude Authorization, Proxy-Authorization, cookies, API keys, and unknown headers.
- Sanitize CR/LF and bound logged header/path lengths. Do not consume, buffer, change, or close response bodies just to log metadata. Preserve exceptions, timeout settings, and retry behavior. Future external clients must use the same policy; do not globally enable unrestricted HTTP wire logging.
