package org.anc.processor.conll

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion
import groovy.transform.Immutable
import org.anc.conf.AnnotationConfig
import org.anc.conf.AnnotationType
import org.anc.index.api.Index
import org.anc.index.IndexFactory

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.QueryParam
import javax.xml.ws.Response
import java.lang.reflect.Array

/**
 * Created by danmccormack on 10/7/14.
 */

//Is in /private/var/corpora/MASC-3.0.0   Not sure if that is the same

// conll can handle all combinations of annotations
def AcceptableAntns = ["PENN", "SENTENCES", "BIBER", "C5", "C7", "CB",
                       "CONTENT", "EVENT", "FN", "FNTOK", "HEPPLE", "LOGICAL",
                       "MPQA", "NC", "NE", "PTB", "NONE", "PTBTOK",
                       "SLATE_COREF", "VC"]

@Path('/annotations')
class Annotations
{
    @GET
    Response Process(@QueryParam('annotations') String annotations,
                     @QueryParam('docID') String docID)
    {
        if (validAnnotations(annotations))
        {
            //process docID accordingly
        }
    }
}

/**
 * Check the requested annotations
 * INPUT: An array of, Array<String> type, of the annotations
 * OUTPUT: True/False whether the annotations are valid for conll processing
 */
def validAnnotations (antnArray)
{
    boolean returnval = true
    for (antn in antnArray)
    {
        // if any of the annotations in the array equal the current annotation
        if (!AcceptableAntns.any().equals(antn))
        {
            returnval = false
            break
        }
    }
    return returnval
}
