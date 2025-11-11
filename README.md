# Embabel Java Greeting Sample

This is a **minimal Embabel + Spring Boot** sample project in Java that you can open in VS Code.

It defines a single agent, `GreetingAgent`, which takes free-text input and produces a structured
`Greeting` object using an LLM.

## Prerequisites

- Java 17+
- Maven 3.9+
- An LLM provider configured for Embabel. Common options:
  - OpenAI (`OPENAI_API_KEY` environment variable)
  - Ollama (local models) with Spring AI

At minimum, export one of (example for OpenAI):

```bash
export OPENAI_API_KEY=your_key_here
```

## Running from VS Code

1. Open the folder `embabel-java-greeting-sample` in VS Code.
2. Let VS Code detect the Maven project (Java extensions recommended).
3. In the Maven panel or the Run view, run the `EmbabelDemoApplication` main class,
   or from a terminal in the project root run:

   ```bash
   mvn spring-boot:run
   ```

4. When the app starts with the `shell` profile, you can talk to your agent from the Embabel shell.
   For example:

   ```text
   execute "Say hello to Alice"
   ```

   Embabel will select the `greeting-agent`, call the `greet` action and return a `Greeting`
   object with a generated `message` field.

## Using the Embabel shell

- Show help: `help`
- Run a task (preferred): `execute "Say hello to Alice"`
- Alias: `x "Say hi to Bob"`
- Show prompts sent to the LLM: `execute "Say hi to Bob" -p`
- Reuse the previous state/blackboard: `execute "Another hello for Alice" -s`
- Open mode (multi‑step execution): `execute "Do X with Y" -o`
- Inspect last result: `blackboard`
- List agents/actions: `agents`, `actions greeting-agent`
- Exit: `exit`

Notes
- Put multi-word intents in quotes after `execute`.
- There is no `call` command in this shell.
- Some shell builds do not support targeting flags like `-n` (agent) or `-a` (action). If `execute --help` does not show them, simply use `execute "..."` and the platform will auto‑select `greeting-agent.greet` in this sample.

## Changing the model

In `src/main/resources/application.yml` change:

```yaml
embabel:
  models:
    default-llm: gpt-4.1-mini
```

to any model available in your environment, for example:

- `gpt-4o`
- `gpt-4.1`
- `mistral:latest` (if using Ollama)

Then restart the application.

## Build and run from the terminal

- Package the app: `mvn -DskipTests package`
- Run the jar: `java -jar target/embabel-java-greeting-sample-0.0.1-SNAPSHOT.jar`

Environment variable for OpenAI (choose one):
- PowerShell (current session): `$env:OPENAI_API_KEY = "your_key_here"`
- Bash: `export OPENAI_API_KEY=your_key_here`

If your network requires repository credentials or a proxy, fill `settings.local.xml` and build with:
`mvn -s settings.local.xml -DskipTests package`

## Optional tweaks

- Reduce native access warnings on Java 21+:
  - Maven: set `MAVEN_OPTS` to `--enable-native-access=ALL-UNNAMED`
    - PowerShell: `$env:MAVEN_OPTS = "--enable-native-access=ALL-UNNAMED"`
  - Running the jar: `java --enable-native-access=ALL-UNNAMED -jar target/embabel-java-greeting-sample-0.0.1-SNAPSHOT.jar`
- Disable Star Wars theme: change `@EnableAgents(loggingTheme = LoggingThemes.STAR_WARS)` to `LoggingThemes.DEFAULT` in `src/main/java/com/example/embabeldemo/EmbabelDemoApplication.java`, then rebuild.
