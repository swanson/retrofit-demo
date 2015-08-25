package test;

import com.swanson.octodroid.GitHub;
import com.swanson.octodroid.MainActivity;
import com.swanson.octodroid.Owner;
import com.swanson.octodroid.Repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.shadows.ShadowToast;
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;

import static org.fest.assertions.api.ANDROID.assertThat;

@RunWith(CustomTestRunner.class)
public class MainActivityTest {

    private MainActivity activity;
    
    @Mock
    private GitHub mockApi;
    
    @Captor
    private ArgumentCaptor<Callback<List<Repository>>> cb;
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        
        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        activity = controller.get();
        activity.setApi(mockApi);
        
        controller.create();
    }
	
    @Test
    public void shouldFillAdapterWithReposFromApi() throws Exception {
        Mockito.verify(mockApi).repositories(Mockito.anyString(), cb.capture());
        
        List<Repository> testRepos = new ArrayList<Repository>();
        testRepos.add(new Repository("rails", "ruby", new Owner("dhh")));
        testRepos.add(new Repository("android", "java", new Owner("google")));
        
        cb.getValue().success(testRepos, null);
        
        assertThat(activity.getListAdapter()).hasCount(2);
    }

    @Test
    public void shouldToastSadMessageIfNoRepos() throws Exception {
        Mockito.verify(mockApi).repositories(Mockito.anyString(), cb.capture());
        
        List<Repository> noRepos = new ArrayList<Repository>();
        
        cb.getValue().success(noRepos, null);
        
        assertThat(ShadowToast.getTextOfLatestToast()).contains("No repos :(");
        assertThat(activity.getListAdapter()).isEmpty();
    }
    
    @Test
    public void shouldToastIfApiError() throws Exception {
        Mockito.verify(mockApi).repositories(Mockito.anyString(), cb.capture());
        
        cb.getValue().failure(null);
        
        assertThat(ShadowToast.getTextOfLatestToast()).contains("Failed");
    }

}
