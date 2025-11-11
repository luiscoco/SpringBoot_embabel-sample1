package com.example.embabeldemo.agent;

import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.common.OperationContext;
import com.embabel.agent.domain.io.UserInput;
import com.example.embabeldemo.domain.Greeting;

@Agent(
        name = "greeting-agent",
        description = "Creates a friendly greeting based on what the user asks."
)
public class GreetingAgent {

    @Action
    @AchievesGoal(description = "Greet the user in a friendly sentence")
    public Greeting greet(UserInput userInput, OperationContext context) {
        String prompt = """
                You are a friendly assistant.
                Read the following user input and respond with a short, warm greeting.
                Only return the greeting text, nothing else.

                User input: %s
                """.formatted(userInput.getContent().trim());

        return context.ai()
                // You can change this to another model name that your setup supports
                .withLlm("gpt-4.1-mini")
                .createObject(prompt, Greeting.class);
    }
}
