package listener;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import utils.ExcelReportUtil;

public class FeatureListener implements ConcurrentEventListener {
    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, this::handleTestCaseStarted);
    }

    private void handleTestCaseStarted(TestCaseStarted event) {
        String uri = event.getTestCase().getUri().toString(); // e.g., "features/login.feature"
        String[] parts = uri.replace("\\", "/").split("/");
        String fileName = parts[parts.length - 1].replace(".feature", "");
        ExcelReportUtil.executedFeatures.add(capitalize(fileName));
    }

    private String capitalize(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}