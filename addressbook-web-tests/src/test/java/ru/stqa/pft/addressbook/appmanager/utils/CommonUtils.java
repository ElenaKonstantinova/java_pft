package ru.stqa.pft.addressbook.appmanager.utils;

import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

public class CommonUtils {

    public static Groups compareGroups(Groups before, Groups after, GroupData group) {
        return before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()));
    }
}
