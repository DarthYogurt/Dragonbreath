package com.flipdog.filebrowser.k;

import android.webkit.MimeTypeMap;
import com.flipdog.commons.a.ax;
import java.util.Hashtable;
import java.util.Map;

public class b
{
  private static b d;
  private Map<String, String> a = null;
  private MimeTypeMap b = null;
  private boolean c = false;

  public static b a()
  {
    if (d == null)
      d = new b();
    return d;
  }

  private final void a(Hashtable<String, String> paramHashtable, String paramString1, String paramString2)
  {
    paramHashtable.put(paramString2, paramString1);
  }

  private Hashtable<String, String> b()
  {
    Hashtable localHashtable = new Hashtable();
    a(localHashtable, "application/msword", "doc");
    a(localHashtable, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
    a(localHashtable, "application/msword", "dot");
    a(localHashtable, "application/vnd.openxmlformats-officedocument.wordprocessingml.template", "dotx");
    a(localHashtable, "application/vnd.ms-excel", "xls");
    a(localHashtable, "application/vnd.ms-excel", "xlt");
    a(localHashtable, "application/vnd.ms-excel", "xlt");
    a(localHashtable, "application/vnd.ms-excel", "xla");
    a(localHashtable, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
    a(localHashtable, "application/vnd.openxmlformats-officedocument.spreadsheetml.template", "xltx");
    a(localHashtable, "application/vnd.ms-powerpoint", "ppt");
    a(localHashtable, "application/vnd.ms-powerpoint", "pot");
    a(localHashtable, "application/vnd.ms-powerpoint", "pps");
    a(localHashtable, "application/vnd.ms-powerpoint", "ppa");
    a(localHashtable, "application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx");
    a(localHashtable, "application/vnd.openxmlformats-officedocument.presentationml.template", "potx");
    a(localHashtable, "application/vnd.openxmlformats-officedocument.presentationml.slideshow", "ppsx");
    a(localHashtable, "application/vnd.ms-powerpoint", "ppax");
    a(localHashtable, "application/vnd.oasis.opendocument.presentation", "odp");
    a(localHashtable, "application/vnd.ms-xpsdocument", "xps");
    a(localHashtable, "application/pdf", "pdf");
    a(localHashtable, "application/rar", "rar");
    a(localHashtable, "application/zip", "zip");
    a(localHashtable, "application/x-tar", "tar");
    a(localHashtable, "application/x-7z-compressed", "7z");
    a(localHashtable, "application/x-7z-compressed", "s7z");
    a(localHashtable, "application/vnd.ms-cab-compressed", "cab");
    a(localHashtable, "application/vnd.ms-cab-compressed", "cab");
    a(localHashtable, "application/vnd.ms-cab-compressed", "lzh");
    a(localHashtable, "application/vnd.ms-cab-compressed", "arj");
    a(localHashtable, "audio/basic", "snd");
    a(localHashtable, "audio/midi", "mid");
    a(localHashtable, "audio/midi", "midi");
    a(localHashtable, "audio/midi", "kar");
    a(localHashtable, "audio/mpeg", "mpga");
    a(localHashtable, "audio/mpeg", "mpega");
    a(localHashtable, "audio/mpeg", "mp2");
    a(localHashtable, "audio/mpeg", "mp3");
    a(localHashtable, "audio/mpeg", "m4a");
    a(localHashtable, "audio/mpegurl", "m3u");
    a(localHashtable, "audio/prs.sid", "sid");
    a(localHashtable, "audio/x-aiff", "aif");
    a(localHashtable, "audio/x-aiff", "aiff");
    a(localHashtable, "audio/x-aiff", "aifc");
    a(localHashtable, "audio/x-gsm", "gsm");
    a(localHashtable, "audio/x-mpegurl", "m3u");
    a(localHashtable, "audio/x-ms-wma", "wma");
    a(localHashtable, "audio/x-ms-wax", "wax");
    a(localHashtable, "audio/x-pn-realaudio", "ra");
    a(localHashtable, "audio/x-pn-realaudio", "rm");
    a(localHashtable, "audio/x-pn-realaudio", "ram");
    a(localHashtable, "audio/x-realaudio", "ra");
    a(localHashtable, "audio/x-scpls", "pls");
    a(localHashtable, "audio/x-sd2", "sd2");
    a(localHashtable, "audio/x-wav", "wav");
    a(localHashtable, "image/x-emf", "emf");
    a(localHashtable, "image/bmp", "bmp");
    a(localHashtable, "image/gif", "gif");
    a(localHashtable, "image/ico", "cur");
    a(localHashtable, "image/ico", "ico");
    a(localHashtable, "image/ief", "ief");
    a(localHashtable, "image/jpeg", "jpeg");
    a(localHashtable, "image/jpeg", "jpg");
    a(localHashtable, "image/jpeg", "jpe");
    a(localHashtable, "image/pcx", "pcx");
    a(localHashtable, "image/png", "png");
    a(localHashtable, "image/svg+xml", "svg");
    a(localHashtable, "image/svg+xml", "svgz");
    a(localHashtable, "image/tiff", "tiff");
    a(localHashtable, "image/tiff", "tif");
    a(localHashtable, "image/vnd.djvu", "djvu");
    a(localHashtable, "image/vnd.djvu", "djv");
    a(localHashtable, "image/vnd.wap.wbmp", "wbmp");
    a(localHashtable, "image/x-cmu-raster", "ras");
    a(localHashtable, "image/x-coreldraw", "cdr");
    a(localHashtable, "image/x-coreldrawpattern", "pat");
    a(localHashtable, "image/x-coreldrawtemplate", "cdt");
    a(localHashtable, "image/x-corelphotopaint", "cpt");
    a(localHashtable, "image/x-icon", "ico");
    a(localHashtable, "image/x-jg", "art");
    a(localHashtable, "image/x-jng", "jng");
    a(localHashtable, "image/x-ms-bmp", "bmp");
    a(localHashtable, "image/x-photoshop", "psd");
    a(localHashtable, "image/x-portable-anymap", "pnm");
    a(localHashtable, "image/x-portable-bitmap", "pbm");
    a(localHashtable, "image/x-portable-graymap", "pgm");
    a(localHashtable, "image/x-portable-pixmap", "ppm");
    a(localHashtable, "image/x-rgb", "rgb");
    a(localHashtable, "image/x-xbitmap", "xbm");
    a(localHashtable, "image/x-xpixmap", "xpm");
    a(localHashtable, "image/x-xwindowdump", "xwd");
    a(localHashtable, "text/html", "htm");
    a(localHashtable, "text/html", "html");
    a(localHashtable, "text/html", "shtml");
    a(localHashtable, "text/html", "stm");
    a(localHashtable, "text/plain", "txt");
    a(localHashtable, "text/plain", "log");
    a(localHashtable, "text/plain", "asc");
    a(localHashtable, "text/plain", "text");
    a(localHashtable, "text/plain", "diff");
    a(localHashtable, "text/plain", "pot");
    a(localHashtable, "text/richtext", "rtx");
    a(localHashtable, "text/rtf", "rtf");
    a(localHashtable, "text/texmacs", "ts");
    a(localHashtable, "text/text", "phps");
    a(localHashtable, "text/tab-separated-values", "tsv");
    a(localHashtable, "text/x-bibtex", "bib");
    a(localHashtable, "text/x-boo", "boo");
    a(localHashtable, "text/x-c++hdr", "h++");
    a(localHashtable, "text/x-c++hdr", "hpp");
    a(localHashtable, "text/x-c++hdr", "hxx");
    a(localHashtable, "text/x-c++hdr", "hh");
    a(localHashtable, "text/x-c++src", "c++");
    a(localHashtable, "text/x-c++src", "cpp");
    a(localHashtable, "text/x-c++src", "cxx");
    a(localHashtable, "text/x-chdr", "h");
    a(localHashtable, "text/x-component", "htc");
    a(localHashtable, "text/x-csh", "csh");
    a(localHashtable, "text/x-csrc", "c");
    a(localHashtable, "text/x-dsrc", "d");
    a(localHashtable, "text/x-haskell", "hs");
    a(localHashtable, "text/x-java", "java");
    a(localHashtable, "text/x-literate-haskell", "lhs");
    a(localHashtable, "text/x-moc", "moc");
    a(localHashtable, "text/x-pascal", "p");
    a(localHashtable, "text/x-pascal", "pas");
    a(localHashtable, "text/x-pcs-gcd", "gcd");
    a(localHashtable, "text/x-setext", "etx");
    a(localHashtable, "text/x-tcl", "tcl");
    a(localHashtable, "text/x-tex", "tex");
    a(localHashtable, "text/x-tex", "ltx");
    a(localHashtable, "text/x-tex", "sty");
    a(localHashtable, "text/x-tex", "cls");
    a(localHashtable, "text/x-vcalendar", "vcs");
    a(localHashtable, "text/x-vcard", "vcf");
    a(localHashtable, "video/3gpp", "3gp");
    a(localHashtable, "video/3gpp", "3g2");
    a(localHashtable, "video/dl", "dl");
    a(localHashtable, "video/dv", "dif");
    a(localHashtable, "video/dv", "dv");
    a(localHashtable, "video/fli", "fli");
    a(localHashtable, "video/mpeg", "mpeg");
    a(localHashtable, "video/mpeg", "mpg");
    a(localHashtable, "video/mpeg", "mpe");
    a(localHashtable, "video/mp4", "mp4");
    a(localHashtable, "video/mpeg", "VOB");
    a(localHashtable, "video/quicktime", "qt");
    a(localHashtable, "video/quicktime", "mov");
    a(localHashtable, "video/vnd.mpegurl", "mxu");
    a(localHashtable, "video/x-la-asf", "lsf");
    a(localHashtable, "video/x-la-asf", "lsx");
    a(localHashtable, "video/x-mng", "mng");
    a(localHashtable, "video/x-ms-asf", "asf");
    a(localHashtable, "video/x-ms-asf", "asx");
    a(localHashtable, "video/x-ms-wm", "wm");
    a(localHashtable, "video/x-ms-wmv", "wmv");
    a(localHashtable, "video/x-ms-wmx", "wmx");
    a(localHashtable, "video/x-ms-wvx", "wvx");
    a(localHashtable, "video/x-msvideo", "avi");
    a(localHashtable, "video/x-sgi-movie", "movie");
    a(localHashtable, "application/andrew-inset", "ez");
    a(localHashtable, "application/dsptype", "tsp");
    a(localHashtable, "application/futuresplash", "spl");
    a(localHashtable, "application/hta", "hta");
    a(localHashtable, "application/mac-binhex40", "hqx");
    a(localHashtable, "application/mac-compactpro", "cpt");
    a(localHashtable, "application/mathematica", "nb");
    a(localHashtable, "application/msaccess", "mdb");
    a(localHashtable, "application/oda", "oda");
    a(localHashtable, "application/ogg", "ogg");
    a(localHashtable, "application/pgp-keys", "key");
    a(localHashtable, "application/pgp-signature", "pgp");
    a(localHashtable, "application/pics-rules", "prf");
    a(localHashtable, "application/rdf+xml", "rdf");
    a(localHashtable, "application/rss+xml", "rss");
    a(localHashtable, "application/vnd.android.package-archive", "apk");
    a(localHashtable, "application/vnd.cinderella", "cdy");
    a(localHashtable, "application/vnd.ms-pki.stl", "stl");
    a(localHashtable, "application/vnd.oasis.opendocument.database", "odb");
    a(localHashtable, "application/vnd.oasis.opendocument.formula", "odf");
    a(localHashtable, "application/vnd.oasis.opendocument.graphics", "odg");
    a(localHashtable, "application/vnd.oasis.opendocument.graphics-template", "otg");
    a(localHashtable, "application/vnd.oasis.opendocument.image", "odi");
    a(localHashtable, "application/vnd.oasis.opendocument.spreadsheet", "ods");
    a(localHashtable, "application/vnd.oasis.opendocument.spreadsheet-template", "ots");
    a(localHashtable, "application/vnd.oasis.opendocument.text", "odt");
    a(localHashtable, "application/vnd.oasis.opendocument.text-master", "odm");
    a(localHashtable, "application/vnd.oasis.opendocument.text-template", "ott");
    a(localHashtable, "application/vnd.oasis.opendocument.text-web", "oth");
    a(localHashtable, "application/vnd.rim.cod", "cod");
    a(localHashtable, "application/vnd.smaf", "mmf");
    a(localHashtable, "application/vnd.stardivision.calc", "sdc");
    a(localHashtable, "application/vnd.stardivision.draw", "sda");
    a(localHashtable, "application/vnd.stardivision.impress", "sdd");
    a(localHashtable, "application/vnd.stardivision.impress", "sdp");
    a(localHashtable, "application/vnd.stardivision.math", "smf");
    a(localHashtable, "application/vnd.stardivision.writer", "sdw");
    a(localHashtable, "application/vnd.stardivision.writer", "vor");
    a(localHashtable, "application/vnd.stardivision.writer-global", "sgl");
    a(localHashtable, "application/vnd.sun.xml.calc", "sxc");
    a(localHashtable, "application/vnd.sun.xml.calc.template", "stc");
    a(localHashtable, "application/vnd.sun.xml.draw", "sxd");
    a(localHashtable, "application/vnd.sun.xml.draw.template", "std");
    a(localHashtable, "application/vnd.sun.xml.impress", "sxi");
    a(localHashtable, "application/vnd.sun.xml.impress.template", "sti");
    a(localHashtable, "application/vnd.sun.xml.math", "sxm");
    a(localHashtable, "application/vnd.sun.xml.writer", "sxw");
    a(localHashtable, "application/vnd.sun.xml.writer.global", "sxg");
    a(localHashtable, "application/vnd.sun.xml.writer.template", "stw");
    a(localHashtable, "application/vnd.visio", "vsd");
    a(localHashtable, "application/x-abiword", "abw");
    a(localHashtable, "application/x-apple-diskimage", "dmg");
    a(localHashtable, "application/x-bcpio", "bcpio");
    a(localHashtable, "application/x-bittorrent", "torrent");
    a(localHashtable, "application/x-cdf", "cdf");
    a(localHashtable, "application/x-cdlink", "vcd");
    a(localHashtable, "application/x-chess-pgn", "pgn");
    a(localHashtable, "application/x-cpio", "cpio");
    a(localHashtable, "application/x-debian-package", "deb");
    a(localHashtable, "application/x-debian-package", "udeb");
    a(localHashtable, "application/x-director", "dcr");
    a(localHashtable, "application/x-director", "dir");
    a(localHashtable, "application/x-director", "dxr");
    a(localHashtable, "application/x-dms", "dms");
    a(localHashtable, "application/x-doom", "wad");
    a(localHashtable, "application/x-dvi", "dvi");
    a(localHashtable, "application/x-flac", "flac");
    a(localHashtable, "application/x-font", "pfa");
    a(localHashtable, "application/x-font", "pfb");
    a(localHashtable, "application/x-font", "gsf");
    a(localHashtable, "application/x-font", "pcf");
    a(localHashtable, "application/x-font", "pcf.Z");
    a(localHashtable, "application/x-freemind", "mm");
    a(localHashtable, "application/x-futuresplash", "spl");
    a(localHashtable, "application/x-gnumeric", "gnumeric");
    a(localHashtable, "application/x-go-sgf", "sgf");
    a(localHashtable, "application/x-graphing-calculator", "gcf");
    a(localHashtable, "application/x-gtar", "gtar");
    a(localHashtable, "application/x-gtar", "tgz");
    a(localHashtable, "application/x-gtar", "taz");
    a(localHashtable, "application/x-hdf", "hdf");
    a(localHashtable, "application/x-ica", "ica");
    a(localHashtable, "application/x-internet-signup", "ins");
    a(localHashtable, "application/x-internet-signup", "isp");
    a(localHashtable, "application/x-iphone", "iii");
    a(localHashtable, "application/x-iso9660-image", "iso");
    a(localHashtable, "application/x-jmol", "jmz");
    a(localHashtable, "application/x-kchart", "chrt");
    a(localHashtable, "application/x-killustrator", "kil");
    a(localHashtable, "application/x-koan", "skp");
    a(localHashtable, "application/x-koan", "skd");
    a(localHashtable, "application/x-koan", "skt");
    a(localHashtable, "application/x-koan", "skm");
    a(localHashtable, "application/x-kpresenter", "kpr");
    a(localHashtable, "application/x-kpresenter", "kpt");
    a(localHashtable, "application/x-kspread", "ksp");
    a(localHashtable, "application/x-kword", "kwd");
    a(localHashtable, "application/x-kword", "kwt");
    a(localHashtable, "application/x-latex", "latex");
    a(localHashtable, "application/x-lha", "lha");
    a(localHashtable, "application/x-lzh", "lzh");
    a(localHashtable, "application/x-lzx", "lzx");
    a(localHashtable, "application/x-maker", "frm");
    a(localHashtable, "application/x-maker", "maker");
    a(localHashtable, "application/x-maker", "frame");
    a(localHashtable, "application/x-maker", "fb");
    a(localHashtable, "application/x-maker", "book");
    a(localHashtable, "application/x-maker", "fbdoc");
    a(localHashtable, "application/x-mif", "mif");
    a(localHashtable, "application/x-ms-wmd", "wmd");
    a(localHashtable, "application/x-ms-wmz", "wmz");
    a(localHashtable, "application/x-msi", "msi");
    a(localHashtable, "application/x-ns-proxy-autoconfig", "pac");
    a(localHashtable, "application/x-nwc", "nwc");
    a(localHashtable, "application/x-object", "o");
    a(localHashtable, "application/x-oz-application", "oza");
    a(localHashtable, "application/x-pkcs7-certreqresp", "p7r");
    a(localHashtable, "application/x-pkcs7-crl", "crl");
    a(localHashtable, "application/x-quicktimeplayer", "qtl");
    a(localHashtable, "application/x-shar", "shar");
    a(localHashtable, "application/x-stuffit", "sit");
    a(localHashtable, "application/x-sv4cpio", "sv4cpio");
    a(localHashtable, "application/x-sv4crc", "sv4crc");
    a(localHashtable, "application/x-texinfo", "texinfo");
    a(localHashtable, "application/x-texinfo", "texi");
    a(localHashtable, "application/x-troff", "t");
    a(localHashtable, "application/x-troff", "roff");
    a(localHashtable, "application/x-troff-man", "man");
    a(localHashtable, "application/x-ustar", "ustar");
    a(localHashtable, "application/x-wais-source", "src");
    a(localHashtable, "application/x-wingz", "wz");
    a(localHashtable, "application/x-webarchive", "webarchive");
    a(localHashtable, "application/x-x509-ca-cert", "crt");
    a(localHashtable, "application/x-xcf", "xcf");
    a(localHashtable, "application/x-xfig", "fig");
    a(localHashtable, "model/iges", "iges");
    a(localHashtable, "model/mesh", "msh");
    a(localHashtable, "model/mesh", "mesh");
    a(localHashtable, "model/mesh", "silo");
    a(localHashtable, "text/calendar", "ics");
    a(localHashtable, "text/calendar", "icz");
    a(localHashtable, "text/comma-separated-values", "csv");
    a(localHashtable, "text/css", "css");
    a(localHashtable, "text/h323", "323");
    a(localHashtable, "text/iuls", "uls");
    a(localHashtable, "text/mathml", "mml");
    a(localHashtable, "model/iges", "igs");
    a(localHashtable, "x-conference/x-cooltalk", "ice");
    a(localHashtable, "x-epoc/x-sisx-app", "sisx");
    a(localHashtable, "application/xml", "xml");
    return localHashtable;
  }

  private String c(String paramString)
  {
    if (this.a == null)
      this.a = b();
    return (String)this.a.get(paramString);
  }

  private String d(String paramString)
  {
    if (!this.c)
    {
      this.c = true;
      this.b = MimeTypeMap.getSingleton();
    }
    if (this.b == null)
      return null;
    return this.b.getMimeTypeFromExtension(paramString);
  }

  public String a(String paramString)
  {
    String str = f.a(paramString);
    if (str == null)
      return null;
    return b(str);
  }

  public String b(String paramString)
  {
    String str1 = paramString.toLowerCase();
    String str2;
    if (ax.a(str1))
      str2 = null;
    do
    {
      return str2;
      str2 = d(str1);
    }
    while (str2 != null);
    return c(str1);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.b
 * JD-Core Version:    0.6.2
 */