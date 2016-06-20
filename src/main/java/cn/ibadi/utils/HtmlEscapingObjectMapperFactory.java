package cn.ibadi.utils;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.FactoryBean;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HtmlEscapingObjectMapperFactory implements FactoryBean<ObjectMapper> {

    private final ObjectMapper objectMapper;

    @SuppressWarnings("deprecation")
	public HtmlEscapingObjectMapperFactory() {
        objectMapper = new ObjectMapper();
        objectMapper.getJsonFactory().setCharacterEscapes(new HTMLCharacterEscapes());
    }

    public ObjectMapper getObject() throws Exception {
        return objectMapper;
    }

    public Class<?> getObjectType() {
        return ObjectMapper.class;
    }

    public boolean isSingleton() {
        return true;
    }

    public static class HTMLCharacterEscapes extends CharacterEscapes {

        /**
		 * 
		 */
		private static final long serialVersionUID = 385391968103525186L;
		private final int[] asciiEscapes;

        public HTMLCharacterEscapes() {
            // start with set of characters known to require escaping (double-quote, backslash etc)
            asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
            // and force escaping of a few others:
            asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
            asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
            asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
            asciiEscapes['"'] = CharacterEscapes.ESCAPE_CUSTOM;
            asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
        }


        @Override
        public int[] getEscapeCodesForAscii() {
            return asciiEscapes;
        }

        // and this for others; we don't need anything special here
        @Override
        public SerializableString getEscapeSequence(int ch) {
            return new SerializedString(StringEscapeUtils.escapeHtml(Character.toString((char) ch)));

        }
    }
}