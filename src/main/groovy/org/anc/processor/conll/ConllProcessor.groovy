package org.anc.processor.conll

import org.anc.processor.Abstract.AbstractProcessor

import javax.ws.rs.Path

/**
 * Created by danmccormack on 10/31/14.
 */
@Path("/conll")
class ConllProcessor extends AbstractProcessor{

    public ConllProcessor() {
        super(["f.penn", "f.sentences", "f.biber", "f.c5", "f.c7", "f.cb",
               "f.content", "f.event", "f.hepple", "f.logical",
               "f.mpqa", "f.nc", "f.ne", "f.none","f.slate_coref",
               "f.vc", "f.ptb", "f.ptbtok", "f.fn", "f.fntok", "f.s"] as List<String>)
        processor = new org.anc.tool.conll.ConllProcessor();
    }
}
