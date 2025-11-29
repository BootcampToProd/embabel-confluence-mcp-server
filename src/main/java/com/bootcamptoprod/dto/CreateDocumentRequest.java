package com.bootcamptoprod.dto;

public record CreateDocumentRequest(String spaceKey, String title, String content) {
}