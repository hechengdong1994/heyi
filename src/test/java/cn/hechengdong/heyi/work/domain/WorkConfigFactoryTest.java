package cn.hechengdong.heyi.work.domain;

import cn.hechengdong.heyi.work.impl.SpringStepManager;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.anyString;

public class WorkConfigFactoryTest {

    @Test
    public void testCreate() {
        StepManager stepManager = Mockito.mock(StepManager.class);
        Mockito.when(stepManager.existsStepExecutor(anyString())).thenReturn(true);

        StepConfig stepConfig = new StepConfig("stepName", "stepType");
        WorkConfig workConfig = new WorkConfig("workName", Lists.newArrayList(stepConfig));
        WorkConfig result = new WorkConfigFactory(stepManager).create(workConfig);
        Assert.assertEquals(workConfig, result);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateForNullPointerExceptionByIllegalStepConfig() {
        StepConfig stepConfig1 = new StepConfig(null, null);
        StepConfig stepConfig2 = new StepConfig("", null);
        StepConfig stepConfig3 = new StepConfig(null, "");
        StepConfig stepConfig4 = new StepConfig("", "");
        WorkConfig workConfig = new WorkConfig("workName", Lists.newArrayList(stepConfig1, stepConfig2, stepConfig3, stepConfig4));
        new WorkConfigFactory(new SpringStepManager()).create(workConfig);
        Assert.fail();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateForNullPointerExceptionByBlankWorkConfigName() {
        StepConfig stepConfig = new StepConfig("stepName", "stepType");
        WorkConfig workConfig = new WorkConfig("", Lists.newArrayList(stepConfig));
        new WorkConfigFactory(new SpringStepManager()).create(workConfig);
        Assert.fail();
    }

    @Test(expected = NullPointerException.class)
    public void testCreateForNullPointerExceptionByNullWorkConfigName() {
        StepConfig stepConfig = new StepConfig("stepName", "stepType");
        WorkConfig workConfig = new WorkConfig(null, Lists.newArrayList(stepConfig));
        new WorkConfigFactory(new SpringStepManager()).create(workConfig);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateForIllegalArgumentExceptionByNullStepConfigOfWorkConfig() {
        WorkConfig workConfig = new WorkConfig("workName", null);
        new WorkConfigFactory(new SpringStepManager()).create(workConfig);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateForIllegalArgumentExceptionByEmptyStepConfigOfWorkConfig() {
        WorkConfig workConfig = new WorkConfig("workName", Lists.newArrayList());
        new WorkConfigFactory(new SpringStepManager()).create(workConfig);
        Assert.fail();
    }
}
