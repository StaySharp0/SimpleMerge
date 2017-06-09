package view.Test;

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

import static org.hamcrest.Matchers.endsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assume.assumeTrue;

import static org.junit.Assert.assertTrue;

public class TestOpenFile extends GuiTest {
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

        FXTestUtils.launchApp(TestOpenFile.TestProgramInfoWindow.class); // You can add start parameters here
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
    public void TestOpenFile(){
        String file1 = "s1 left.txt";
        String file2 = "s1 right.txt";

        click("#btnLeftFileOpen");
        if(GuiTest.find("#btnLeftFileEdit").isDisable()) {
            assertTrue(GuiTest.find("#btnLeftFileEdit").isDisable());
            assertTrue(GuiTest.find("#btnLeftFileSave").isDisable());
        }
        if(GuiTest.find("#btnRightFileEdit").isDisable()) {
            assertTrue(GuiTest.find("#btnRightFileEdit").isDisable());
            assertTrue(GuiTest.find("#btnRightFileSave").isDisable());
        }
        if(((TextField) GuiTest.find("#fieldLeftFile")).getText() == "") {
            System.out.println("왼쪽 파일에서 아무 파일도 선택하지 않는다");
            click("#btnLeftFileOpen");
            type(KeyCode.ESCAPE);
            assertEquals(((TextField) GuiTest.find("#fieldLeftFile")).getText(), "");
            click("#btnLeftFileEdit");
            assertTrue(GuiTest.find("#btnLeftFileEdit").isDisable());
            click("#btnLeftFileSave");
            assertTrue(GuiTest.find("#btnLeftFileSave").isDisable());
        }

        System.out.println("왼쪽 파일을 선택한다");
        click("#btnLeftFileOpen");
        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        //type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
        type("\\Users\\503\\Downloads\\samples").type(KeyCode.ENTER);
        type(file1).type(KeyCode.ENTER);
        System.out.println(((TextField)GuiTest.find("#fieldLeftFile")).getText()+"를 불러옵니다");
        assertEquals(((TextField)GuiTest.find("#fieldLeftFile")).getText(), file1);

        System.out.println("선택후 왼쪽 Save, Edit 작동하는 것 확인");
        assertFalse(GuiTest.find("#btnLeftFileEdit").isDisable());
        assertFalse(GuiTest.find("#btnLeftFileSave").isDisable());

        if(((TextField) GuiTest.find("#fieldRightFile")).getText() == "") {
            System.out.println("오른쪽 파일에서 아무 파일도 선택하지 않는다");
            click("#btnRightFileOpen");
            type(KeyCode.ESCAPE);
            assertEquals(((TextField) GuiTest.find("#fieldRightFile")).getText(), "");
            click("#btnRightFileEdit");
            assertTrue(GuiTest.find("#btnRightFileEdit").isDisable());
            click("#btnRightFileSave");
            assertTrue(GuiTest.find("#btnRightFileSave").isDisable());
        }

        System.out.println("오른쪽 파일을 선택한다");
        click("#btnRightFileOpen");
        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        //type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
        type("\\Users\\503\\Downloads\\samples").type(KeyCode.ENTER);
        type(file2).type(KeyCode.ENTER);
        System.out.println(((TextField)GuiTest.find("#fieldRightFile")).getText()+"를 불러옵니다");
        assertEquals(((TextField)GuiTest.find("#fieldRightFile")).getText(), file2);

        System.out.println("양쪽 파일의 확장자가 .txt파일이 맞는지 검사합니다");
        assert(((TextField)GuiTest.find("#fieldLeftFile")).getText(). endsWith(".txt"));
        assert(((TextField)GuiTest.find("#fieldRightFile")).getText(). endsWith(".txt"));


        System.out.println("선택후 오른쪽 Save, Edit 작동하는 것 확인");
        assertFalse(GuiTest.find("#btnRightFileEdit").isDisable());
        assertFalse(GuiTest.find("#btnRightFileSave").isDisable());


    }


}
