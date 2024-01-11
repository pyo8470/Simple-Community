package com.example.seb45pre011.tag;

import java.util.List;

public interface TagService {
  Tag createTag(TagDto tagDto);
  List<Tag> getAllTags();
  Tag getTagByName(String name);
  Tag updateTagDescription(Long tagId, String description);
}

