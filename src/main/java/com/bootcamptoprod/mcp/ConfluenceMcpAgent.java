package com.bootcamptoprod.mcp;

import com.bootcamptoprod.dto.ConfluenceResponse;
import com.bootcamptoprod.dto.CreateDocumentRequest;
import com.bootcamptoprod.dto.DocumentIdRequest;
import com.bootcamptoprod.dto.SpaceKeyRequest;
import com.bootcamptoprod.service.ConfluenceService;
import com.embabel.agent.api.annotation.AchievesGoal;
import com.embabel.agent.api.annotation.Action;
import com.embabel.agent.api.annotation.Agent;
import com.embabel.agent.api.annotation.Export;
import com.embabel.agent.domain.io.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Agent(description = "An MCP agent exposing Confluence operations.")
public class ConfluenceMcpAgent {

    private static final Logger logger = LoggerFactory.getLogger(ConfluenceMcpAgent.class);

    private final ConfluenceService confluenceService;

    public ConfluenceMcpAgent(ConfluenceService confluenceService) {
        this.confluenceService = confluenceService;
        logger.info("ConfluenceMcpAgent initialized");
    }

    /**
     * List all spaces in Confluence.
     */
    @Action
    @AchievesGoal(
            description = "List all spaces in Confluence.",
            export = @Export(remote = true, name = "listSpaces", startingInputTypes = UserInput.class)
    )
    public ConfluenceResponse listSpaces() {
        logger.info("Agent action: listSpaces");
        return new ConfluenceResponse(confluenceService.listSpaces());
    }

    /**
     * List documents in a specific space.
     */
    @Action
    @AchievesGoal(
            description = "List documents in a Confluence space.",
            export = @Export(remote = true, name = "listDocumentsInSpace", startingInputTypes = SpaceKeyRequest.class)
    )
    public ConfluenceResponse listDocumentsInSpace(SpaceKeyRequest spaceKeyRequest) {
        logger.info("Agent action: listDocumentsInSpace for space='{}'", spaceKeyRequest.spaceKey());
        return new ConfluenceResponse(confluenceService.listDocumentsInSpace(spaceKeyRequest.spaceKey()));
    }

    /**
     * Create a new document with specified content.
     */
    @Action
    @AchievesGoal(
            description = "Create a new Confluence page in a space.",
            export = @Export(remote = true, name = "createDocument", startingInputTypes = CreateDocumentRequest.class)
    )
    public ConfluenceResponse createDocument(CreateDocumentRequest createDocumentRequest) {
        logger.info("Agent action: createDocument in space='{}' title='{}'", createDocumentRequest.spaceKey(), createDocumentRequest.title());
        return new ConfluenceResponse(confluenceService.createDocument(createDocumentRequest.spaceKey(), createDocumentRequest.title(), createDocumentRequest.content()));
    }

    /**
     * Get version history for a page/document.
     */
    @Action
    @AchievesGoal(
            description = "Fetch page history for a Confluence document.",
            export = @Export(remote = true, name = "getPageHistory", startingInputTypes = DocumentIdRequest.class)
    )
    public ConfluenceResponse getPageHistory(DocumentIdRequest documentIdRequest) {
        logger.info("Agent action: getPageHistory for documentId='{}'", documentIdRequest.documentId());
        return new ConfluenceResponse(confluenceService.getPageHistory(documentIdRequest.documentId()));
    }

    /**
     * Get metadata for a specific document.
     */
    @Action
    @AchievesGoal(
            description = "Fetch metadata for a Confluence document.",
            export = @Export(remote = true, name = "getDocumentMetadata", startingInputTypes = DocumentIdRequest.class)
    )
    public ConfluenceResponse getDocumentMetadata(DocumentIdRequest documentIdRequest) {
        logger.info("Agent action: getDocumentMetadata for documentId='{}'", documentIdRequest.documentId());
        return new ConfluenceResponse(confluenceService.getDocumentMetadata(documentIdRequest.documentId()));
    }
}
