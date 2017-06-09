package view.Test;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import view.Main;
import com.google.common.util.concurrent.SettableFuture;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.loadui.testfx.utils.FXTestUtils;
import org.loadui.testfx.utils.UserInputDetector;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class TestCompareFile extends GuiTest {
    private static final SettableFuture<Stage> stageFuture = SettableFuture.create();

    protected static class TestProgramInfoWindow extends Main {
        public TestProgramInfoWindow() {
            super();
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            super.start(primaryStage);
            stageFuture.set(primaryStage);
        }
    }

    @Before
    @Override
    public void setupStage() throws Throwable {
        assumeTrue(!UserInputDetector.instance.hasDetectedUserInput());

        FXTestUtils.launchApp(TestCompareFile.TestProgramInfoWindow.class); // You can add start parameters here
        try {
            stage = targetWindow(stageFuture.get(25, TimeUnit.SECONDS));
            FXTestUtils.bringToFront(stage);
        } catch (Exception e) {
            throw new RuntimeException("Unable to show stage", e);
        }
    }

    @Override
    protected Parent getRootNode() {
        return stage.getScene().getRoot();
    }

    @Test
    public void TestListClick() throws InterruptedException {
        String file1 = "s2 left.txt";
        String file2 = "s2 right.txt";

        System.out.println("Compare 버튼이 안 눌리는지 확인합니다");
        assertTrue(GuiTest.find("#btnCompare").isDisable());
        click("#btnCompare");

        System.out.println("왼쪽 파일을 선택한다");
        click("#btnLeftFileOpen");
        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        //type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
        type("\\Users\\503\\Downloads\\samples").type(KeyCode.ENTER);
        type(file1).type(KeyCode.ENTER);
        System.out.println(((TextField) GuiTest.find("#fieldLeftFile")).getText() + "를 불러옵니다");
        assertEquals(((TextField) GuiTest.find("#fieldLeftFile")).getText(), file1);

        System.out.println("Compare 버튼이 안 눌리는지 확인합니다");
        assertTrue(GuiTest.find("#btnCompare").isDisable());
        click("#btnCompare");

        System.out.println("오른쪽 파일을 선택한다");
        click("#btnRightFileOpen");
        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        //type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
        type("\\Users\\503\\Downloads\\samples").type(KeyCode.ENTER);
        type(file2).type(KeyCode.ENTER);
        System.out.println(((TextField)GuiTest.find("#fieldRightFile")).getText()+"를 불러옵니다");
        assertEquals(((TextField)GuiTest.find("#fieldRightFile")).getText(), file2);


        System.out.println("Compare 버튼이 눌리는지 확인합니다");
        assertFalse(GuiTest.find("#btnCompare").isDisable());
        click("#btnCompare");

        ListView list = find("#listLeft");
        list.getSelectionModel().select(0);

        click(list.getSelectionModel().getSelectedItems());
        moveBy(0, 15);
        click();
        moveBy(0, 20);
        click();
    }

}
