package com.inviggoproject.service;

import com.inviggoproject.model.Advert;
import com.inviggoproject.model.AdvertCategory;
import com.inviggoproject.model.User;
import com.inviggoproject.repository.AdvertCategoryRepository;
import com.inviggoproject.repository.AdvertRepository;
import com.inviggoproject.service.impl.AdvertServiceImpl;
import com.inviggoproject.util.TestConstants;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AdvertServiceTest {

    @Mock
    private AdvertRepository advertRepository;

    @Mock
    private AdvertCategoryRepository advertCategoryRepository;

    @Mock
    private AuthService authService;

    @Mock
    private Advert advert =
            new Advert(TestConstants.ADVERT_NAME,
                    TestConstants.ADVERT_DESCRIPTION, TestConstants.IMAGE_URL,
                    TestConstants.PRICE, TestConstants.CITY);

    @InjectMocks
    private AdvertServiceImpl advertService;

    @Test
    public void createCreatesAdvertSuccessfully() {
        when(advertCategoryRepository.findByName(TestConstants.CATEGORIE_NAME_TOOL)).thenReturn(new AdvertCategory());
        when(authService.getActiveUser()).thenReturn(new User());
        advertService.create(advert, TestConstants.CATEGORIE_NAME_TOOL);
        verify(advertRepository, times(1)).save(any());
        verifyNoMoreInteractions(advertRepository);
    }
}
