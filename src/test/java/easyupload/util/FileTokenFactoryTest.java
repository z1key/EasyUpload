package easyupload.util;

import easyupload.entity.User;
import org.junit.Test;
import org.springframework.util.StringUtils;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FileTokenFactoryTest {

    @Test
    public void generate() {
        User mockUser = mock(User.class);
        FileToken token = FileTokenFactory.generate(mockUser);

        assertEquals(token.getUser(), mockUser);
        assertTrue(token.getFolder().isRoot());
        assertFalse(StringUtils.isEmpty(token.getHash()));
    }
}