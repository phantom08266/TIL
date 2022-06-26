package xunit;

import java.util.ArrayList;
import java.util.List;

public class TestSuit {

    private List<WasRun> wasRuns = new ArrayList<>();


    public void add(WasRun wasRun) {
        wasRuns.add(wasRun);
    }

    public void run(TestResult result) {
        wasRuns.forEach(wasRun -> wasRun.run(result));
    }
}
