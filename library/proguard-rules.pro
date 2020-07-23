# XML
-keep public class * extends org.xml.sax.XMLReader

-dontwarn javax.swing.tree.DefaultTreeModel
-dontwarn javax.swing.tree.TreeNode
-dontwarn java.lang.instrument.ClassFileTransformer
-dontwarn javax.swing.table.AbstractTableModel
-dontwarn sun.misc.SignalHandler

-dontwarn org.xmlpull.v1.XmlPullParser
-dontwarn org.xmlpull.v1.XmlSerializer

-keep class org.xmlpull.v1.* { *; }
-keep class org.apache.xerces.jaxp.** {*;}
-keep class org.dom4j.** {*;}
-keep class org.xmlpull.v1.** {*;}
-keep class org.xml.sax.** {*;}
-keep class javax.xml.parsers.** {*;}
-keep class org.jaxen.saxpath.** {*;}
