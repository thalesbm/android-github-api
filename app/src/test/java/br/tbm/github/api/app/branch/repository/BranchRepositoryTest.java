package br.tbm.github.api.app.branch.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import br.tbm.github.api.app.branch.BranchMVP;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BranchRepositoryTest {

    @Mock
    BranchMVP.Presenter mPresenter;

    @Before
    public void setUp() {
    }

    @Test
    public void searchBranchesInServer_Test() {
    }
}