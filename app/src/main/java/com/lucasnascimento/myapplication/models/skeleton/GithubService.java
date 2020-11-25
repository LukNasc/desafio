package com.lucasnascimento.myapplication.models.skeleton;

import java.util.ArrayList;
import java.util.List;

public class GithubService {
    private List<Repositories> items = new ArrayList<>();
    private long total_count;

    public List<Repositories> getItems() {
        return items;
    }

    public void setItems(List<Repositories> items) {
        this.items = items;
    }

    public long getTotal_count() {
        return total_count;
    }

    public void setTotal_count(long total_count) {
        this.total_count = total_count;
    }

    public class Repositories {
        private long id;
        private String name;
        private String description;
        private long forks_count;
        private long stargazers_count;
        private Owner owner;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public long getForks_count() {
            return forks_count;
        }

        public void setForks_count(long forks_count) {
            this.forks_count = forks_count;
        }

        public long getStargazers_count() {
            return stargazers_count;
        }

        public void setStargazers_count(long stargazers_count) {
            this.stargazers_count = stargazers_count;
        }

        public Owner getOwner() {
            return owner;
        }

        public void setOwner(Owner owner) {
            this.owner = owner;
        }

        public class Owner {
            private String avatar_url;
            private String login;

            public String getAvatar_url() {
                return avatar_url;
            }

            public void setAvatar_url(String avatar_url) {
                this.avatar_url = avatar_url;
            }

            public String getLogin() {
                return login;
            }

            public void setLogin(String login) {
                this.login = login;
            }
        }
    }
}
