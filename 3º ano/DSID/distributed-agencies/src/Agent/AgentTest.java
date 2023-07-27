package Agent;

import java.io.Serializable;

public class AgentTest implements Runnable, Serializable {
    private String agentName;

    public AgentTest(String agentName) {
        this.agentName = agentName;
    }

    @Override
    public void run() {
        System.out.println("Agent " + agentName + " is running!");

        // Simulando o processamento de tarefas
        for (int i = 1; i <= 5; i++) {
            String taskMessage = receiveTask();
            String result = processTask(taskMessage);
            sendResult(result);
        }

        System.out.println("Agent " + agentName + " finished!");
    }

    private String receiveTask() {
        // Simulação da recepção de uma tarefa (poderia ser uma mensagem recebida da agência)
        return "Task_" + agentName;
    }

    private String processTask(String taskMessage) {
        // Simulação do processamento de uma tarefa (poderia ser uma tarefa complexa)
        return "Result of " + taskMessage;
    }

    private void sendResult(String result) {
        // Simulação do envio do resultado da tarefa (poderia ser uma mensagem enviada à agência)
        System.out.println("Agent " + agentName + " sending result: " + result);
    }
}
