package cn.hechengdong.heyi.work.domain;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Matchers.anyString;

@PrepareForTest(Work.class)
public class WorkFactoryTest {

    private StepManager stepManager;

    @Before
    public void before() throws Exception {
        PowerMockito.whenNew(Work.class).withAnyArguments().thenReturn(Mockito.mock(Work.class));

        stepManager = Mockito.mock(StepManager.class);
    }

    @Test
    public void testCreate() {
        Mockito.when(stepManager.getStepExecutor(anyString())).thenReturn(Mockito.mock(StepExecutor.class));

        StepConfig stepConfig = new StepConfig("stepName", "stepType");
        WorkConfig workConfig = new WorkConfig("workName", Lists.newArrayList(stepConfig));
        Map<String, Object> params = new HashMap<>();

        Work result = new WorkFactory(stepManager).create(workConfig, params);

        Assert.assertNotNull(result);
        Assert.assertEquals(workConfig.getName(), result.getName());
        Assert.assertEquals(result.getParams(), params);
        Assert.assertNotNull(result.getStepChain());
        Assert.assertEquals(workConfig.getStepConfigs().size(), result.getStepChain().size());
    }

    @Test(expected = NullPointerException.class)
    public void testCreateForNullPointerExceptionByNullWorkConfig() {
        new WorkFactory(stepManager).create(null, null);

        Assert.fail();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateForNullPointerExceptionByNullStepConfigsInWorkConfig() {
        WorkConfig workConfig = new WorkConfig("", null);

        new WorkFactory(stepManager).create(workConfig, null);

        Assert.fail();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateForNullPointerExceptionByNonexistentStepExecutorInWorkConfig() {
        Mockito.when(stepManager.getStepExecutor(anyString())).thenReturn(null);

        StepConfig stepConfig = new StepConfig("stepName", "stepType");
        WorkConfig workConfig = new WorkConfig("workName", Lists.newArrayList(stepConfig));

        new WorkFactory(stepManager).create(workConfig, null);

        Assert.fail();
    }

}
