package com.newlight77.core.note

import com.fasterxml.jackson.annotation.JsonIgnore

data class NoteDomain(var id: Long? = null,
                      var title: String? = null,
                      var text: String? = null,
                      @JsonIgnore var author: String? = null)