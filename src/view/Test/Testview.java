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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assume.assumeTrue;

import  org.junit.Assert.*;
import static org.loadui.testfx.Assertions.assertNodeExists;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
//import sample.ClickApplication;

//import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;
import org.junit.rules.TestName;
import org.junit.Rule;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Testview extends GuiTest {
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

    @Rule
    public TestName testName = new TestName();

    @Test
    public void test01OpenFileMain(){

        System.out.println(testName.getMethodName());
        String file1 = "firstfile.txt";
        String file2 = "secondfile.txt";

        System.out.println("왼쪽 파일을 선택한다");
        if(GuiTest.find("#btnLeftFileEdit").isDisable()) {
            assertTrue(GuiTest.find("#btnLeftFileEdit").isDisable());
            assertTrue(GuiTest.find("#btnLeftFileSave").isDisable());
        }
        if(GuiTest.find("#btnRightFileEdit").isDisable()) {
            assertTrue(GuiTest.find("#btnRightFileEdit").isDisable());
            assertTrue(GuiTest.find("#btnRightFileSave").isDisable());
        }

        click("#btnLeftFileOpen");
        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
        type(file1).type(KeyCode.ENTER);
        //assertTrue(((TextField)GuiTest.find("#fieldLeftFile")).getText().equals("filename1"));
//verifythat

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
    public void test03OpenFileSide(){

    }

    @Test
    public void test04ListClick(){

    }

    @Test
    public void test02TextAreaSwitch(){
        String file1 = "firstfile.txt";
        String file2 = "secondfile.txt";
        click("#btnLeftFileEdit");click("#btnRightFileEdit");click("#btnLeftFileEdit");
        click("#btnLeftFileEdit");
        click("#btnRightFileEdit");


        if(GuiTest.find("#btnLeftFileEdit").isDisable()){
            click("#btnLeftFileOpen");
            type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
            type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
            type(file1).type(KeyCode.ENTER);
        }

        System.out.println("edit 버튼을 누를 시 수정 가능한지 확인");
        assertFalse(GuiTest.find("#fieldLeftFile").isDisable());
        click("#btnLeftFileEdit");
        System.out.println("edit 버튼을 다시 누를 시 수정 불가능한지 확인");
        assertFalse(GuiTest.find("#fieldLeftFile").isDisable());

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
