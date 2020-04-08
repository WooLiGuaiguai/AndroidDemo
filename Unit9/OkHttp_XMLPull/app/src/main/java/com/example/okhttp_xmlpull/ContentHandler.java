package com.example.okhttp_xmlpull;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ContentHandler extends DefaultHandler {
    private String nodeName;
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;
    @Override//初始化
    public void startDocument() throws SAXException {
        id=new StringBuilder();
        name=new StringBuilder();
        version=new StringBuilder();
    }
    @Override//开始解析某个节点
    public void startElement(String uri, String localName, String qName, Attributes attributes)
            throws SAXException {
        nodeName=localName;
    }
    @Override//解析节点中具体内容
    public void characters(char[] ch, int start, int length) throws SAXException {
        if("id".equals(nodeName)){
            id.append(ch,start,length);
        }else if("name".equals(nodeName)) {
            name.append(ch, start, length);
        } else if ("version".equals(nodeName)) {
            version.append(ch, start, length);
        }
    }
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if("app".equals(nodeName)){
            Log.d("ContentHandler","id is"+id.toString().trim());//去掉首尾空格的字符串
            Log.d("ContentHandler", "name is " + name.toString().trim());
            Log.d("ContentHandler", "version is " + version.toString().trim());
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
        super.endElement(uri, localName, qName);
    }
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
