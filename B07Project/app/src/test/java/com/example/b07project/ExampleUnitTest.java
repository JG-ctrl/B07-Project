package com.example.b07project;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    @Mock
    ActivityLoginPageModel model;

    @Mock
    ActivityLoginPageView view;

    @InjectMocks
    ActivityLoginPagePresenter presenter;

    @Before
    public void setup() {
        // Setup behavior for queryDBShopper
        doAnswer(invocation -> {
            String username = invocation.getArgument(0);
            String password = invocation.getArgument(1);

            // Wrong username or password
            if (username.isEmpty() || password.isEmpty()) {
                presenter.loginShopper(false);
            } else presenter.loginShopper(username.equals("correct_username") && password.equals("correct_password")); // Correct username and password
            return null;
        }).when(model).queryDBShopper(anyString(), anyString(), any());

        // Setup behavior for queryDBOwner
        doAnswer(invocation -> {
            String username = invocation.getArgument(0);
            String password = invocation.getArgument(1);

            // Wrong username or password
            if (username.isEmpty() || password.isEmpty()) {
                presenter.loginOwner(false);
            } else presenter.loginOwner(username.equals("correct_username") && password.equals("correct_password")); // Correct username and password
            return null;
        }).when(model).queryDBOwner(anyString(), anyString(), any());
    }

    /***************** TEST 1: EMPTY FIELDS *****************/

    // 1.1: empty username, wrong password
    @Test
    public void checkEmptyUsernameShopper() {
        presenter.checkDBShopper("", "password");
        verify(view).writeInvalid();
    }
    @Test
    public void checkEmptyUsernameOwner() {
        presenter.checkDBOwner("", "password");
        verify(view).writeInvalid();
    }

    // 1.2: wrong username, empty password
    @Test
    public void checkEmptyPasswordShopper() {
        presenter.checkDBShopper("username", "");
        verify(view).writeInvalid();
    }
    @Test
    public void checkEmptyPasswordOwner() {
        presenter.checkDBOwner("username", "");
        verify(view).writeInvalid();
    }

    // 1.3: both empty
    @Test
    public void checkEmptyUsernamePasswordShopper() {
        presenter.checkDBOwner("", "");
        verify(view).writeInvalid();
    }
    @Test
    public void checkEmptyUsernamePasswordOwner() {
        presenter.checkDBOwner("", "");
        verify(view).writeInvalid();
    }

    // 1.4: correct username, empty password
    @Test
    public void checkCorrectUsernameEmptyPasswordShopper() {
        // Provide the correct username and an incorrect password
        presenter.checkDBShopper("correct_username", "");
        verify(view).writeInvalid();
    }
    @Test
    public void checkCorrectUsernameEmptyPasswordOwner() {
        // Provide the correct username and an incorrect password
        presenter.checkDBOwner("correct_username", "");
        verify(view).writeInvalid();
    }

    /***************** TEST 2: INCORRECT INFO *****************/

    // 2.1: correct username, wrong password
    @Test
    public void checkWrongPasswordShopper() {
        // Provide the correct username and an incorrect password
        presenter.checkDBShopper("correct_username", "wrong_password");
        verify(view).writeInvalid();
    }
    @Test
    public void checkWrongPasswordOwner() {
        // Provide the correct username and an incorrect password
        presenter.checkDBOwner("correct_username", "wrong_password");
        verify(view).writeInvalid();
    }

    // 2.2: wrong username, correct password
    @Test
    public void checkWrongUsernameShopper() {
        // Provide the correct username and an incorrect password
        presenter.checkDBShopper("wrong_username", "correct_password");
        verify(view).writeInvalid();
    }
    @Test
    public void checkWrongUsernameOwner() {
        // Provide the correct username and an incorrect password
        presenter.checkDBOwner("wrong_username", "correct_password");
        verify(view).writeInvalid();
    }

    // 2.3: both wrong
    @Test
    public void checkWrongUsernamePasswordShopper() {
        // Provide the correct username and an incorrect password
        presenter.checkDBShopper("wrong_username", "wrong_password");
        verify(view).writeInvalid();
    }
    @Test
    public void checkWrongUsernamePasswordOwner() {
        // Provide the correct username and an incorrect password
        presenter.checkDBOwner("wrong_username", "wrong_password");
        verify(view).writeInvalid();
    }

    /***************** TEST 3: CORRECT LOGIN *****************/
    // 3.1: correct username, correct password
    @Test
    public void checkCorrectShopper() {
        // Provide the correct username and an incorrect password
        presenter.checkDBShopper("correct_username", "correct_password");
        verify(view).openActivity(ActivityShopperView1.class);
    }
    @Test
    public void checkCorrectOwner() {
        // Provide the correct username and an incorrect password
        presenter.checkDBOwner("correct_username", "correct_password");
        verify(view).openActivity(ActivityOwnerView1.class);
    }
}