package com.bestpractices.spamdetector.service;

import com.bestpractices.spamdetector.entity.SpamWord;

import java.util.List;

public interface SpamWordsManager {
    List<SpamWord> getSpamWords();
}
