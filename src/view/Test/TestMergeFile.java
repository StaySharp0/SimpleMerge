package view.Test;

import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.loadui.testfx.controls.ListViews;
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

import static javafx.geometry.VerticalDirection.DOWN;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

public class TestMergeFile extends GuiTest {
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

        FXTestUtils.launchApp(TestMergeFile.TestProgramInfoWindow.class); // You can add start parameters here
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
    public void TestListClick(){
        String file1 = "s7 left.txt";
        String file2 = "s7 right.txt";

        System.out.println("Merge 버튼이 눌리지 않는지 확인합니다");
        assertTrue(GuiTest.find("#btnMtoLeft").isDisable());
        click("#btnMtoLeft");
        assertTrue(GuiTest.find("#btnMtoRight").isDisable());
        click("#btnMtoRight");

        System.out.println("왼쪽 파일을 선택한다");
        click("#btnLeftFileOpen");
        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        type("\\Users\\503\\Downloads\\samples").type(KeyCode.ENTER);
        type(file1).type(KeyCode.ENTER);
        System.out.println(((TextField) GuiTest.find("#fieldLeftFile")).getText() + "를 불러옵니다");
        assertEquals(((TextField) GuiTest.find("#fieldLeftFile")).getText(), file1);

        System.out.println("오른쪽 파일을 선택한다");
        click("#btnRightFileOpen");
        type("C").type(KeyCode.SHIFT, KeyCode.SEMICOLON).type((KeyCode.ENTER));
        type("\\Users\\503\\Downloads\\samples").type(KeyCode.ENTER);
        type(file2).type(KeyCode.ENTER);
        System.out.println(((TextField)GuiTest.find("#fieldRightFile")).getText()+"를 불러옵니다");
        assertEquals(((TextField)GuiTest.find("#fieldRightFile")).getText(), file2);


        System.out.println("Compare 버튼이 눌리는지 확인합니다");
        assertFalse(GuiTest.find("#btnCompare").isDisable());
        click("#btnCompare");

        //System.out.println("Merge 버튼이 눌리는지 확인합니다");
        //assertFalse(GuiTest.find("#btnMtoLeft").isDisable());
        //assertFalse(GuiTest.find("#btnMtoRight").isDisable());


        click("#btnMtoLeft");
        click("#btnMtoRight");
        ListView list = find("#listLeft");
        ListView list2 = find("#listRight");

        System.out.println("Merge");
        moveBy(0, 20);
        moveBy(0, 20);
        moveBy(0, 20);
        moveBy(0, 20);
        moveBy(0, 20);
        click();
        System.out.println("Merge left -> right");
        click("#btnMtoLeft");
        System.out.println("Merge right -> left");
        click("#btnMtoRight");
        moveBy(0, 20);
        moveBy(0, 20);
        moveBy(0, 20);

        
        System.out.println("스크롤을 아래로 내립니다.");
        scroll( 3, DOWN );

        System.out.println("Merge left -> right");
        click("#btnMtoLeft");
        System.out.println("Merge right -> left");
        click("#btnMtoRight");

        /*System.out.println("leftList의 클릭한 부분의 item"+list.getSelectionModel().getSelectedItem());
        //click(list2);
        System.out.println("Merge 후 두 값이 동일한지 확인");
        assertEquals(list.getSelectionModel().getSelectedItem(), list.getSelectionModel().getSelectedItem());
        System.out.println("RightList의 클릭한 부분의 item"+list2.getSelectionModel().getSelectedItem());*/



    }

}
