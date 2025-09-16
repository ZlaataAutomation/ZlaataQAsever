package listener;


import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;
import io.cucumber.plugin.event.TestStepFinished;
import runner.FailedTestRunner;
import io.cucumber.plugin.event.Status;

public class CustomTestListener implements EventListener {
    private boolean isFailed = false;
    private String scenarioName;

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
        publisher.registerHandlerFor(TestStepFinished.class, this::handleTestStepFinished);
        publisher.registerHandlerFor(TestRunFinished.class, this::handleTestRunFinished);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        scenarioName = event.getTestCase().getName();
        isFailed = false;
    }

    private void handleTestStepFinished(TestStepFinished event) {
        if (event.getResult().getStatus().is(Status.FAILED)) {
            isFailed = true;
        }
    }

    private void handleTestRunFinished(TestRunFinished event) {
        if (isFailed) {
            FailedTestRunner.writeFailedTestCasesToExcel(scenarioName, false);
        } else {
            FailedTestRunner.writeFailedTestCasesToExcel(scenarioName, true);
        }
    }
}