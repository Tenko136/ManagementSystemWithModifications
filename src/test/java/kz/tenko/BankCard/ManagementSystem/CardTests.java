package kz.tenko.BankCard.ManagementSystem;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;

import static org.junit.jupiter.api.Assertions.assertThrows;


public class CardTests {

//    CardServiceImpl underTest;
//    UserDAO userDAO;
//    CardDAO cardDAO;
//
//    @BeforeEach
//    public void setUp() {
//        userDAO = Mockito.mock(UserDAO.class);
//        cardDAO = Mockito.mock(CardDAO.class);
//        underTest = new CardServiceImpl(cardDAO, userDAO);
//
//    }

//    @Test
//    public void findCardsForAdminTest() {
//        Authentication authentication = Mockito.mock(Authentication.class);
//        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
//        when(authentication.getAuthorities()).thenReturn((Collection) grantedAuthorities);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        underTest.findCards(new FindCardsRequestDTO());
//
//        verify(cardDAO, times(1)).findCards(Mockito.any(FindCardsRequestDTO.class));
//    }
//
//    @Test
//    public void findCardsForUserTest() {
//        Authentication authentication = Mockito.mock(Authentication.class);
//        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        when(authentication.getAuthorities()).thenReturn((Collection) grantedAuthorities);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        User user = new User("UserName", "userName", grantedAuthorities);
//        when(authentication.getPrincipal()).thenReturn(user);
//        var userEntity = new kz.tenko.BankCard.ManagementSystem.entity.User();
//        userEntity.setId(1L);
//        when(userDAO.findUserByEmail(Mockito.any())).thenReturn(userEntity);
//
//        underTest.findCards(new FindCardsRequestDTO());
//
//        verify(cardDAO, times(1)).findCards(userEntity.getId());
//    }
//
//    @Test
//    public void transferBalanceTest() {
//        String cFrom = "1234123412341234";
//        String cTo = "4321432143214321";
//        long amount = 100;
//
//        Authentication authentication = Mockito.mock(Authentication.class);
//        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        when(authentication.getAuthorities()).thenReturn((Collection) grantedAuthorities);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        User user = new User("UserName", "userName", grantedAuthorities);
//        when(authentication.getPrincipal()).thenReturn(user);
//
//        var userEntity = new kz.tenko.BankCard.ManagementSystem.entity.User();
//        userEntity.setId(1L);
//        when(userDAO.findUserByEmail(Mockito.any())).thenReturn(userEntity);
//
//        List<Card> cardsList = new ArrayList<>();
//        cardsList.add(new Card(1L, cFrom, null, null, 500L));
//        cardsList.add(new Card(1L, cTo, null, null, 500L));
//        when(cardDAO.findCards(userEntity.getId())).thenReturn(cardsList);
//
//        when(cardDAO.findBalance(cFrom)).thenReturn(cardsList.getFirst().getBalance());
//
//        underTest.transferAmount(cFrom, cTo, amount);
//
//        verify(cardDAO, times(1)).changeBalance(cFrom, -amount);
//        verify(cardDAO, times(1)).changeBalance(cTo, amount);
//
//    }
//    @Test
//    public void transferBalanceNegativeTest() {
//        String cFrom = "1234123412341234";
//        String cTo = "4321432143214321";
//        long amount = 100;
//
//        Authentication authentication = Mockito.mock(Authentication.class);
//        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
//        when(authentication.getAuthorities()).thenReturn((Collection) grantedAuthorities);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        User user = new User("UserName", "userName", grantedAuthorities);
//        when(authentication.getPrincipal()).thenReturn(user);
//
//        var userEntity = new kz.tenko.BankCard.ManagementSystem.entity.User();
//        userEntity.setId(1L);
//        when(userDAO.findUserByEmail(Mockito.any())).thenReturn(userEntity);
//
//        List<Card> cardsList = new ArrayList<>();
//        cardsList.add(new Card(1L, cFrom, null, null, 50L));
//        cardsList.add(new Card(1L, cTo, null, null, 500L));
//        when(cardDAO.findCards(userEntity.getId())).thenReturn(cardsList);
//
//        when(cardDAO.findBalance(cFrom)).thenReturn(cardsList.getFirst().getBalance());
//
//        assertThrows(
//                RuntimeException.class,
//                () -> underTest.transferAmount(cFrom, cTo, amount));
//
//    }
}