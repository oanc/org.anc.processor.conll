package org.anc.processor.conll

import org.anc.index.core.IndexImpl
import org.anc.processor.conll.i18n.Messages
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.xces.graf.io.dom.ResourceHeader

import javax.ws.rs.Path


/**
 * Created by danmccormack on 10/31/14.
 */
@Path("/conll")
class ConllProcessor extends AbstractProcessor{
    private static final Logger logger = LoggerFactory.getLogger(ConllProcessor)

    private static final Messages MESSAGES = new Messages()



    public ConllProcessor() {
        processor = new org.anc.tool.conll.ConllProcessor();
        //TODO This path should not be hard coded.
        header = new ResourceHeader(new File("/var/corpora/MASC-3.0.0/resource-header.xml"))
        index = new IndexImpl().loadMasc3Index()
    }

    public ConllProcessor(HashSet<String> acceptable){
        processor = new org.anc.tool.conll.ConllProcessor();
        //TODO This path should not be hard coded.
        header = new ResourceHeader(new File("/var/corpora/MASC-3.0.0/resource-header.xml"))
        index = new IndexImpl().loadMasc3Index()
        ACCEPTABLE = acceptable
    }
}
