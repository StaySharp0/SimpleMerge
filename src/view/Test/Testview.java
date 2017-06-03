package view.Test;

import javafx.scene.input.KeyCode;
import view.*;
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
import static org.junit.Assume.assumeTrue;
import static org.loadui.testfx.Assertions.assertNodeExists;


//import sample.ClickApplication;

//import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

public class Testview extends GuiTest {
    private static final SettableFuture<Stage> stageFuture = SettableFuture.create();

    /**
     * The type Test program info window.
     */
    protected static class TestProgramInfoWindow extends Main {
        /**
         * Instantiates a new Test program info window.
         */
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

        FXTestUtils.launchApp(Testview.TestProgramInfoWindow.class); // You can add start parameters here
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
    public void testOpenFileMain(){
        String file1 = "firstfile.txt";
        String file2 = "secondfile.txt";

        System.out.println("왼쪽 파일을 선택한다");
        assertTrue(GuiTest.find("#btnLeftFileEdit").isDisable());
        assertTrue(GuiTest.find("#btnLeftFileSave").isDisable());
        assertTrue(GuiTest.find("#btnRightFileEdit").isDisable());
        assertTrue(GuiTest.find("#btnRightFileSave").isDisable());

        click("#btnLeftFileOpen");
        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
        type(file1).type(KeyCode.ENTER);
        //assertTrue(((TextField)GuiTest.find("#fieldLeftFile")).getText().equals("filename1"));

        //콜백받는거 확인 //TODO
        //assertEquals("", "FileOpen callback");

        System.out.println("선택후 왼쪽 Save, Edit 작동하는 것 확인");
        assertFalse(GuiTest.find("#btnLeftFileEdit").isDisable());
        assertFalse(GuiTest.find("#btnLeftFileSave").isDisable());


        System.out.println("오른쪽 파일을 선택한다");

        click("#btnRightFileOpen");

        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
        type(file2).type(KeyCode.ENTER);
        //assertTrue(((TextField)GuiTest.find("#fieldRightFile")).getText().equals("filename2"));
        //콜백받는거 확인 //TODO
        //assertEquals("", "FileOpen callback");

        System.out.println("선택후 오른쪽 Save, Edit 작동하는 것 확인");
        assertFalse(GuiTest.find("#btnRightFileEdit").isDisable());
        assertFalse(GuiTest.find("#btnRightFileSave").isDisable());

    }



    @Test
    public void testOpenFileSide(){

    }

    @Test
    public void testListClick(){

    }

    @Test
    public void testTextAreaSwitch(){
        String file1 = "firstfile.txt";
        String file2 = "secondfile.txt";

        //TODO
        //edit 버튼을 누를 시 제대로 판이 바뀌는지 확인할 것.
        assertFalse(GuiTest.find("#fieldLeftFile").isDisable());


        if(GuiTest.find("#btnLeftFileEdit").isDisable()){
            click("#btnLeftFileOpen");
            type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
            type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
            type(file1).type(KeyCode.ENTER);
        }



        if(GuiTest.find("#btnRightFileEdit").isDisable()){
            click("#btnRightFileOpen");
            type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
            type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
            type(file2).type(KeyCode.ENTER);
        }


        click("#btnLeftFileEdit");
        click("#btnRightFileEdit");




    }

}
