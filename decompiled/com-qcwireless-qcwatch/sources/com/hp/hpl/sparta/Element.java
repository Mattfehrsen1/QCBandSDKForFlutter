package com.hp.hpl.sparta;

import com.hp.hpl.sparta.xpath.Step;
import com.hp.hpl.sparta.xpath.XPath;
import com.hp.hpl.sparta.xpath.XPathException;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* loaded from: classes2.dex */
public class Element extends Node {
    private static final boolean DEBUG = false;
    private Vector attributeNames_;
    private Hashtable attributes_;
    private Node firstChild_;
    private Node lastChild_;
    private String tagName_;

    private void checkInvariant() {
    }

    public Element(String str) {
        this.firstChild_ = null;
        this.lastChild_ = null;
        this.attributes_ = null;
        this.attributeNames_ = null;
        this.tagName_ = null;
        this.tagName_ = Sparta.intern(str);
    }

    Element() {
        this.firstChild_ = null;
        this.lastChild_ = null;
        this.attributes_ = null;
        this.attributeNames_ = null;
        this.tagName_ = null;
    }

    @Override // com.hp.hpl.sparta.Node
    public Object clone() {
        return cloneElement(true);
    }

    public Element cloneShallow() {
        return cloneElement(false);
    }

    public Element cloneElement(boolean z) {
        Element element = new Element(this.tagName_);
        Vector vector = this.attributeNames_;
        if (vector != null) {
            Enumeration enumerationElements = vector.elements();
            while (enumerationElements.hasMoreElements()) {
                String str = (String) enumerationElements.nextElement();
                element.setAttribute(str, (String) this.attributes_.get(str));
            }
        }
        if (z) {
            for (Node nextSibling = this.firstChild_; nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
                element.appendChild((Node) nextSibling.clone());
            }
        }
        return element;
    }

    public String getTagName() {
        return this.tagName_;
    }

    public void setTagName(String str) {
        this.tagName_ = Sparta.intern(str);
        notifyObservers();
    }

    public Node getFirstChild() {
        return this.firstChild_;
    }

    public Node getLastChild() {
        return this.lastChild_;
    }

    public Enumeration getAttributeNames() {
        Vector vector = this.attributeNames_;
        if (vector == null) {
            return Document.EMPTY;
        }
        return vector.elements();
    }

    public String getAttribute(String str) {
        Hashtable hashtable = this.attributes_;
        if (hashtable == null) {
            return null;
        }
        return (String) hashtable.get(str);
    }

    public void setAttribute(String str, String str2) {
        if (this.attributes_ == null) {
            this.attributes_ = new Hashtable();
            this.attributeNames_ = new Vector();
        }
        if (this.attributes_.get(str) == null) {
            this.attributeNames_.addElement(str);
        }
        this.attributes_.put(str, str2);
        notifyObservers();
    }

    public void removeAttribute(String str) {
        Hashtable hashtable = this.attributes_;
        if (hashtable == null) {
            return;
        }
        hashtable.remove(str);
        this.attributeNames_.removeElement(str);
        notifyObservers();
    }

    void appendChildNoChecking(Node node) {
        Element parentNode = node.getParentNode();
        if (parentNode != null) {
            parentNode.removeChildNoChecking(node);
        }
        node.insertAtEndOfLinkedList(this.lastChild_);
        if (this.firstChild_ == null) {
            this.firstChild_ = node;
        }
        node.setParentNode(this);
        this.lastChild_ = node;
        node.setOwnerDocument(getOwnerDocument());
    }

    public void appendChild(Node node) {
        if (!canHaveAsDescendent(node)) {
            node = (Element) node.clone();
        }
        appendChildNoChecking(node);
        notifyObservers();
    }

    boolean canHaveAsDescendent(Node node) {
        if (node == this) {
            return false;
        }
        Element parentNode = getParentNode();
        if (parentNode == null) {
            return true;
        }
        return parentNode.canHaveAsDescendent(node);
    }

    private boolean removeChildNoChecking(Node node) {
        for (Node nextSibling = this.firstChild_; nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling.equals(node)) {
                if (this.firstChild_ == nextSibling) {
                    this.firstChild_ = nextSibling.getNextSibling();
                }
                if (this.lastChild_ == nextSibling) {
                    this.lastChild_ = nextSibling.getPreviousSibling();
                }
                nextSibling.removeFromLinkedList();
                nextSibling.setParentNode(null);
                nextSibling.setOwnerDocument(null);
                return true;
            }
        }
        return false;
    }

    public void removeChild(Node node) throws DOMException {
        if (!removeChildNoChecking(node)) {
            throw new DOMException((short) 8, "Cannot find " + node + " in " + this);
        }
        notifyObservers();
    }

    public void replaceChild(Element element, Node node) throws DOMException {
        replaceChild_(element, node);
        notifyObservers();
    }

    public void replaceChild(Text text, Node node) throws DOMException {
        replaceChild_(text, node);
        notifyObservers();
    }

    private void replaceChild_(Node node, Node node2) throws DOMException {
        for (Node nextSibling = this.firstChild_; nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            if (nextSibling == node2) {
                if (this.firstChild_ == node2) {
                    this.firstChild_ = node;
                }
                if (this.lastChild_ == node2) {
                    this.lastChild_ = node;
                }
                node2.replaceInLinkedList(node);
                node.setParentNode(this);
                node2.setParentNode(null);
                return;
            }
        }
        throw new DOMException((short) 8, "Cannot find " + node2 + " in " + this);
    }

    @Override // com.hp.hpl.sparta.Node
    void toString(Writer writer) throws IOException {
        for (Node nextSibling = this.firstChild_; nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            nextSibling.toString(writer);
        }
    }

    @Override // com.hp.hpl.sparta.Node
    public void toXml(Writer writer) throws IOException {
        writer.write("<" + this.tagName_);
        Vector vector = this.attributeNames_;
        if (vector != null) {
            Enumeration enumerationElements = vector.elements();
            while (enumerationElements.hasMoreElements()) {
                String str = (String) enumerationElements.nextElement();
                String str2 = (String) this.attributes_.get(str);
                writer.write(" " + str + "=\"");
                htmlEncode(writer, str2);
                writer.write("\"");
            }
        }
        if (this.firstChild_ == null) {
            writer.write("/>");
            return;
        }
        writer.write(">");
        for (Node nextSibling = this.firstChild_; nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            nextSibling.toXml(writer);
        }
        writer.write("</" + this.tagName_ + ">");
    }

    private XPathVisitor visitor(String str, boolean z) throws XPathException {
        XPath xPath = XPath.get(str);
        if (xPath.isStringValue() != z) {
            throw new XPathException(xPath, "\"" + xPath + "\" evaluates to " + (z ? "evaluates to element not string" : "evaluates to string not element"));
        }
        return new XPathVisitor(this, xPath);
    }

    @Override // com.hp.hpl.sparta.Node
    public Enumeration xpathSelectElements(String str) throws ParseException {
        try {
            return visitor(str, false).getResultEnumeration();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", e);
        }
    }

    @Override // com.hp.hpl.sparta.Node
    public Enumeration xpathSelectStrings(String str) throws ParseException {
        try {
            return visitor(str, true).getResultEnumeration();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", e);
        }
    }

    public boolean xpathEnsure(String str) throws ParseException {
        Element elementXpathSelectElement;
        try {
            if (xpathSelectElement(str) != null) {
                return false;
            }
            XPath xPath = XPath.get(str);
            Enumeration steps = xPath.getSteps();
            int i = 0;
            while (steps.hasMoreElements()) {
                steps.nextElement();
                i++;
            }
            int i2 = i - 1;
            Step[] stepArr = new Step[i2];
            Enumeration steps2 = xPath.getSteps();
            for (int i3 = 0; i3 < i2; i3++) {
                stepArr[i3] = (Step) steps2.nextElement();
            }
            Step step = (Step) steps2.nextElement();
            if (i2 == 0) {
                elementXpathSelectElement = this;
            } else {
                String string = XPath.get(xPath.isAbsolute(), stepArr).toString();
                xpathEnsure(string.toString());
                elementXpathSelectElement = xpathSelectElement(string);
            }
            elementXpathSelectElement.appendChildNoChecking(makeMatching(elementXpathSelectElement, step, str));
            return true;
        } catch (XPathException e) {
            throw new ParseException(str, e);
        }
    }

    @Override // com.hp.hpl.sparta.Node
    public Element xpathSelectElement(String str) throws ParseException {
        try {
            return visitor(str, false).getFirstResultElement();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", e);
        }
    }

    @Override // com.hp.hpl.sparta.Node
    public String xpathSelectString(String str) throws ParseException {
        try {
            return visitor(str, true).getFirstResultString();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Element)) {
            return false;
        }
        Element element = (Element) obj;
        if (!this.tagName_.equals(element.tagName_)) {
            return false;
        }
        Hashtable hashtable = this.attributes_;
        int size = hashtable == null ? 0 : hashtable.size();
        Hashtable hashtable2 = element.attributes_;
        if (size != (hashtable2 == null ? 0 : hashtable2.size())) {
            return false;
        }
        Hashtable hashtable3 = this.attributes_;
        if (hashtable3 != null) {
            Enumeration enumerationKeys = hashtable3.keys();
            while (enumerationKeys.hasMoreElements()) {
                String str = (String) enumerationKeys.nextElement();
                if (!((String) this.attributes_.get(str)).equals((String) element.attributes_.get(str))) {
                    return false;
                }
            }
        }
        Node nextSibling = this.firstChild_;
        Node nextSibling2 = element.firstChild_;
        while (nextSibling != null) {
            if (!nextSibling.equals(nextSibling2)) {
                return false;
            }
            nextSibling = nextSibling.getNextSibling();
            nextSibling2 = nextSibling2.getNextSibling();
        }
        return true;
    }

    @Override // com.hp.hpl.sparta.Node
    protected int computeHashCode() {
        int iHashCode = this.tagName_.hashCode();
        Hashtable hashtable = this.attributes_;
        if (hashtable != null) {
            Enumeration enumerationKeys = hashtable.keys();
            while (enumerationKeys.hasMoreElements()) {
                String str = (String) enumerationKeys.nextElement();
                iHashCode = (((iHashCode * 31) + str.hashCode()) * 31) + ((String) this.attributes_.get(str)).hashCode();
            }
        }
        for (Node nextSibling = this.firstChild_; nextSibling != null; nextSibling = nextSibling.getNextSibling()) {
            iHashCode = (iHashCode * 31) + nextSibling.hashCode();
        }
        return iHashCode;
    }
}
