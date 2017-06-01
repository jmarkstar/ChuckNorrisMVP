package com.jmarkstar.chucknorris.domain.repository.dao;

import com.jmarkstar.chucknorris.domain.model.JokeModel;
import com.jmarkstar.chucknorris.domain.repository.database.dao.JokeDao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created by jmarkstar on 31/05/2017.
 */
@RunWith(PowerMockRunner.class)
public class JokeDaoTest {

    @Mock private JokeDao mJokeDao;

    @Test public void insertJoke(){
        JokeModel joke = new JokeModel();
        joke.setId(1);
        joke.setJoke("ha ha ha ha");

        long success = mJokeDao.insertJoke(joke);
        System.out.println("success: "+success);
    }

}
