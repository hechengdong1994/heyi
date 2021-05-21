package cn.hechengdong.heyi.work.domain;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.Matchers.anyString;

@RunWith(PowerMockRunner.class)
@PrepareForTest({StepManager.class})
public class WorkConfigFactoryTest {

    @Test
    public void testCreate() {
        PowerMockito.mockStatic(StepManager.class);
        PowerMockito.when(StepManager.existsStep(anyString())).thenReturn(true);

        StepConfig stepConfig = new StepConfig("stepName", "stepType");
        WorkConfig workConfig = new WorkConfig("workName", Lists.newArrayList(stepConfig));
        WorkConfig result = WorkConfigFactory.createWorkConfig(workConfig);
        Assert.assertEquals(workConfig, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateForIllegalArgumentExceptionByIllegalStepConfig() {
        StepConfig stepConfig1 = new StepConfig(null, null);
        StepConfig stepConfig2 = new StepConfig("", null);
        StepConfig stepConfig3 = new StepConfig(null, "");
        StepConfig stepConfig4 = new StepConfig("", "");
        WorkConfig workConfig = new WorkConfig("workName", Lists.newArrayList(stepConfig1, stepConfig2, stepConfig3, stepConfig4));
        WorkConfigFactory.createWorkConfig(workConfig);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateForIllegalArgumentExceptionByBlankWorkConfigName() {
        StepConfig stepConfig = new StepConfig("stepName", "stepType");
        WorkConfig workConfig = new WorkConfig("", Lists.newArrayList(stepConfig));
        WorkConfigFactory.createWorkConfig(workConfig);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateForIllegalArgumentExceptionByNullWorkConfigName() {
        StepConfig stepConfig = new StepConfig("stepName", "stepType");
        WorkConfig workConfig = new WorkConfig(null, Lists.newArrayList(stepConfig));
        WorkConfigFactory.createWorkConfig(workConfig);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateForIllegalArgumentExceptionByNullStepConfigOfWorkConfig() {
        WorkConfig workConfig = new WorkConfig("workName", null);
        WorkConfigFactory.createWorkConfig(workConfig);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateForIllegalArgumentExceptionByEmptyStepConfigOfWorkConfig() {
        WorkConfig workConfig = new WorkConfig("workName", Lists.newArrayList());
        WorkConfigFactory.createWorkConfig(workConfig);
        Assert.fail();
    }
}
