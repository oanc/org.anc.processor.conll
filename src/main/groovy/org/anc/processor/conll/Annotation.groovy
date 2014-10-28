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

@Path ('/Annotations')
class Annotations
{
    @GET
    Response Process(@QueryParam('annotations') String annotations,
                     @QueryParam('docID') String docID)
    {
        def AnnotationsArray = parseAnnotations(annotations)

        if (validAnnotations(AnnotationsArray))
        {
            //call the processor with the selected annotations
        }
    }

    //********************************************************************
    //Everything that I would normally put outside, is below this because
    // of errors I couldn't resolve otherwise
    //********************************************************************

    // conll can handle all combinations of annotations
    def AcceptableAntns = ["PENN", "SENTENCES", "BIBER", "C5", "C7", "CB",
                           "CONTENT", "EVENT", "FN", "FNTOK", "HEPPLE", "LOGICAL",
                           "MPQA", "NC", "NE", "PTB", "NONE", "PTBTOK",
                           "SLATE_COREF", "VC"]

    /**
     * Check if the annotations provided are acceptable for conll processing
     * @param antnArray - ArrayList<String> of annotations
     * @return A boolean response if the annotations passed in are acceptable for
     * conll processing.
     */
    boolean validAnnotations(ArrayList<String> antnArray) {
        boolean returnval = true
        for (antn in antnArray) {
            // if any of the annotations in the array equal the current annotation
            if (!AcceptableAntns.any(equals(antn))) {
                returnval = false
                break
            }
        }
        return returnval
    }

    /**
     * Split the comma separated string into an ArrayList<String>
     * @param antnString - The comma separated string of selected annotations
     * @return An ArrayList<String> of the selected annotations
     */
    ArrayList<String> parseAnnotations(String antnString)
    {
        def retArray = antnString.split(',')
        return retArray
    }
}

