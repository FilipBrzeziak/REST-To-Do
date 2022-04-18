package io.github.mat3e;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelloServiceTest {
    private final static String WELCOME = "Hello";
    private final static String FALLBACK_ID_WELCOME = "Hola";
    @Test
    public void testPrepareGreetingNullNameReturnsGreetingFallbackName(){
        var mockRepository = alwaysReturnHelloRepository();
        var SUT = new HelloService(mockRepository);

        //given + when
        var result = SUT.prepareGreeting(null, "-1");

        //then
        assertEquals(WELCOME + " "+HelloService.FALLBACK_NAME+"!",result);
    }
    @Test
    public void testPrepareGreetingNameReturnGreetingName(){
        var SUT = new HelloService();
        //given
        var name = "test";

        //when
        var result = SUT.prepareGreeting(name, "-1");

        //then
        assertEquals(WELCOME + " "+name+"!",result);
    }

    @Test
    public void testPrepareGreetingNullLangReturnsGreetingWithFallbackIdLang(){
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        //given
        var name = "test";

        //when
        var result = SUT.prepareGreeting(null, null);

        //then
        assertEquals(FALLBACK_ID_WELCOME + " "+HelloService.FALLBACK_NAME+"!",result);
    }

    @Test
    public void testPrepareGreetingTextLangReturnsGreetingWithFallbackIdLang(){
        var mockRepository = fallbackLangIdRepository();
        var SUT = new HelloService(mockRepository);
        //given
        var name = "test";

        //when
        var result = SUT.prepareGreeting(null, "abc");

        //then
        assertEquals(FALLBACK_ID_WELCOME + " "+HelloService.FALLBACK_NAME+"!",result);
    }

    @Test
    public void testPrepareGreetingNonExistingLangReturnsGreetingWithFallbackLang(){
        var mockRepository = new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.empty();
            }
        };
        var SUT = new HelloService(mockRepository);

        var name = "test";

        var result = SUT.prepareGreeting(null, "-1");

        assertEquals(HelloService.FALLBACK_LANG.getWelcomeMsg() + " "+HelloService.FALLBACK_NAME+"!",result);
    }

    public LangRepository alwaysReturnHelloRepository(){
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                return Optional.of(new Lang(null, WELCOME, null));
            }
        };
    }

    public LangRepository fallbackLangIdRepository() {
        return new LangRepository() {
            @Override
            Optional<Lang> findById(Integer id) {
                if (id.equals(HelloService.FALLBACK_LANG.getId())) {
                    return Optional.of(new Lang(null, FALLBACK_ID_WELCOME, null));
                }
                return Optional.empty();
            }
        };
    }
}
