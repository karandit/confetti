package org.confetti.tests.fet.engine;

import org.confetti.tests.fet.engine.SpeedCategories.Fast_Under_1_sec;
import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Categories.class)
@IncludeCategory(Fast_Under_1_sec.class)
@SuiteClasses({FETRunnableTest.class})
public class FETRunnableTestSuite {

}
