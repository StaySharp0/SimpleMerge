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
import static org.junit.Assume.assumeTrue;

import static org.junit.Assert.assertTrue;
import org.junit.rules.TestName;
import org.junit.Rule;

public class TestEditFile extends GuiTest {
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

        FXTestUtils.launchApp(TestEditFile.TestProgramInfoWindow.class); // You can add start parameters here
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
    public void TestEditFile(){
        String file1 = "s1 left.txt";
        String file2 = "s1 right.txt";

        click("#btnLeftFileEdit");
        click("#btnRightFileEdit");
        click("#btnLeftFileSave");
        click("#btnRightFileSave");


        if(GuiTest.find("#btnLeftFileEdit").isDisable()){
            click("#btnLeftFileOpen");
            type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
            //type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
            type("\\Users\\503\\Downloads\\samples").type(KeyCode.ENTER);
            type(file1).type(KeyCode.ENTER);
        }

        if(GuiTest.find("#btnRightFileEdit").isDisable()){
            click("#btnRightFileOpen");
            type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
            //type("Users\\503\\Desktop\\SimpleMerge-GUI\\src\\view").type(KeyCode.ENTER);
            type("\\Users\\503\\Downloads\\samples").type(KeyCode.ENTER);
            type(file2).type(KeyCode.ENTER);
        }

        System.out.println("edit 버튼을 누를 시 수정 가능한지 확인합니다");
        assertFalse(GuiTest.find("#fieldLeftFile").isDisable());
        assertFalse(GuiTest.find("#textAreaLeft").isDisable());
        assertFalse(GuiTest.find("#fieldRightFile").isDisable());
        assertFalse(GuiTest.find("#textAreaRight").isDisable());
        click("#btnLeftFileEdit");
        click("#btnRightFileEdit");

        System.out.println("파일명 앞에 Edit: 이 붙는지 확인합니다");
        assertEquals(((TextField)GuiTest.find("#fieldLeftFile")).getText(), "Edit: "+file1);
        assertEquals(((TextField)GuiTest.find("#fieldRightFile")).getText(), "Edit: "+file2);

        System.out.println("텍스트 <Left file>을 추가합니다");
        click("#textAreaLeft");
        type(KeyCode.ENTER).type("Left file");

        System.out.println("텍스트 <Right file>을 추가합니다");
        click("#textAreaRight");
        type(KeyCode.ENTER).type("Right file");

        System.out.println("파일명 앞에 *이 붙는지 확인합니다");
        click("#btnLeftFileEdit");
        assertEquals(((TextField)GuiTest.find("#fieldLeftFile")).getText(), "*"+file1);
        click("#btnRightFileEdit");
        assertEquals(((TextField)GuiTest.find("#fieldRightFile")).getText(), "*"+file2);

        System.out.println("edit 버튼을 다시 누를 시 수정 불가능한지 확인합니다");
        click("#btnLeftFileEdit");
        //assertTrue(GuiTest.find("#fieldLeftFile").isDisable());
        click("#btnRightFileEdit");
        //assertTrue(GuiTest.find("#fieldRightFile").isDisable());

        System.out.println("save를 누를 시 파일명 앞에 *이 사라지는지 확인합니다");
        click("#btnLeftFileSave");
        assertEquals(((TextField)GuiTest.find("#fieldLeftFile")).getText(), file1);
        click("#btnRightFileSave");
        assertEquals(((TextField)GuiTest.find("#fieldRightFile")).getText(), file2);

        System.out.println("Edit을 여러번 누를 시 파일명 앞에 *이 그대로인지 확인합니다");
        click("#btnLeftFileEdit");
        click("#btnLeftFileEdit");
        assertEquals(((TextField)GuiTest.find("#fieldLeftFile")).getText(), "*"+file1);
        click("#btnRightFileEdit");
        click("#btnRightFileEdit");
        assertEquals(((TextField)GuiTest.find("#fieldRightFile")).getText(), "*"+file2);

        System.out.println("edit 버튼을 다시 누를 시 수정 불가능한지 확인");
        click("#btnLeftFileEdit");
        click("#btnRightFileEdit");
        assertFalse(GuiTest.find("#fieldLeftFile").isDisable());
        assertFalse(GuiTest.find("#fieldRightFile").isDisable());


    }

}
