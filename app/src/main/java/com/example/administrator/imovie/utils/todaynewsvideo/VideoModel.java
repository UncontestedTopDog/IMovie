package com.example.administrator.imovie.utils.todaynewsvideo;

/**
 * Created by huangweiliang on 2018/02/12.
 */

public class VideoModel {

    /**
     * data : {"status":10,"user_id":"toutiao","video_id":"f33215507819430dba3a66017110b4ce","validate":"","enable_ssl":false,"poster_url":"http://p3.pstatp.com/origin/5645000f8ab0ee6db4a4","video_duration":298.48,"auto_definition":"480p","video_list":{"video_1":{"definition":"480p","vtype":"mp4","vwidth":854,"vheight":480,"bitrate":404988,"size":17693407,"codec_type":"h264","logo_type":"xigua_share","file_hash":"a5961f4f336a6997d6dea1325435344b","main_url":"aHR0cDovL3YxLXR0Lml4aWd1YS5jb20vOTc3ODUyMmU1ZTBkMmRjNWU3NTRhODU4YzgxZTBjM2EvNWE4MjYwNWMvdmlkZW8vbS8yMjAwMWE2Y2FiOTY4NjA0MjU2YTkyYzVmN2EwZGZhMTkwNjExNTM2NGQ1MDAwMGY3MjdiMTFiZjM1Yy8=","backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNzVhZmJlMDBlZjNlYmRiZmEzZGYzOTU5NzdkMmVjMjgvNWE4MjYwNWMvdmlkZW8vbS8yMjAwMWE2Y2FiOTY4NjA0MjU2YTkyYzVmN2EwZGZhMTkwNjExNTM2NGQ1MDAwMGY3MjdiMTFiZjM1Yy8=","user_video_proxy":1,"socket_buffer":9112140,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10}}}
     * message : success
     * code : 0
     * total : 1
     */

    private DataBean data;
    private String message;
    private int code;
    private int total;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public static class DataBean {
        /**
         * status : 10
         * user_id : toutiao
         * video_id : f33215507819430dba3a66017110b4ce
         * validate :
         * enable_ssl : false
         * poster_url : http://p3.pstatp.com/origin/5645000f8ab0ee6db4a4
         * video_duration : 298.48
         * auto_definition : 480p
         * video_list : {"video_1":{"definition":"480p","vtype":"mp4","vwidth":854,"vheight":480,"bitrate":404988,"size":17693407,"codec_type":"h264","logo_type":"xigua_share","file_hash":"a5961f4f336a6997d6dea1325435344b","main_url":"aHR0cDovL3YxLXR0Lml4aWd1YS5jb20vOTc3ODUyMmU1ZTBkMmRjNWU3NTRhODU4YzgxZTBjM2EvNWE4MjYwNWMvdmlkZW8vbS8yMjAwMWE2Y2FiOTY4NjA0MjU2YTkyYzVmN2EwZGZhMTkwNjExNTM2NGQ1MDAwMGY3MjdiMTFiZjM1Yy8=","backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNzVhZmJlMDBlZjNlYmRiZmEzZGYzOTU5NzdkMmVjMjgvNWE4MjYwNWMvdmlkZW8vbS8yMjAwMWE2Y2FiOTY4NjA0MjU2YTkyYzVmN2EwZGZhMTkwNjExNTM2NGQ1MDAwMGY3MjdiMTFiZjM1Yy8=","user_video_proxy":1,"socket_buffer":9112140,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10}}
         */

        private int status;
        private String user_id;
        private String video_id;
        private String validate;
        private boolean enable_ssl;
        private String poster_url;
        private double video_duration;
        private String auto_definition;
        private VideoListBean video_list;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getValidate() {
            return validate;
        }

        public void setValidate(String validate) {
            this.validate = validate;
        }

        public boolean isEnable_ssl() {
            return enable_ssl;
        }

        public void setEnable_ssl(boolean enable_ssl) {
            this.enable_ssl = enable_ssl;
        }

        public String getPoster_url() {
            return poster_url;
        }

        public void setPoster_url(String poster_url) {
            this.poster_url = poster_url;
        }

        public double getVideo_duration() {
            return video_duration;
        }

        public void setVideo_duration(double video_duration) {
            this.video_duration = video_duration;
        }

        public String getAuto_definition() {
            return auto_definition;
        }

        public void setAuto_definition(String auto_definition) {
            this.auto_definition = auto_definition;
        }

        public VideoListBean getVideo_list() {
            return video_list;
        }

        public void setVideo_list(VideoListBean video_list) {
            this.video_list = video_list;
        }

        public static class VideoListBean {
            /**
             * video_1 : {"definition":"480p","vtype":"mp4","vwidth":854,"vheight":480,"bitrate":404988,"size":17693407,"codec_type":"h264","logo_type":"xigua_share","file_hash":"a5961f4f336a6997d6dea1325435344b","main_url":"aHR0cDovL3YxLXR0Lml4aWd1YS5jb20vOTc3ODUyMmU1ZTBkMmRjNWU3NTRhODU4YzgxZTBjM2EvNWE4MjYwNWMvdmlkZW8vbS8yMjAwMWE2Y2FiOTY4NjA0MjU2YTkyYzVmN2EwZGZhMTkwNjExNTM2NGQ1MDAwMGY3MjdiMTFiZjM1Yy8=","backup_url_1":"aHR0cDovL3Y3LnBzdGF0cC5jb20vNzVhZmJlMDBlZjNlYmRiZmEzZGYzOTU5NzdkMmVjMjgvNWE4MjYwNWMvdmlkZW8vbS8yMjAwMWE2Y2FiOTY4NjA0MjU2YTkyYzVmN2EwZGZhMTkwNjExNTM2NGQ1MDAwMGY3MjdiMTFiZjM1Yy8=","user_video_proxy":1,"socket_buffer":9112140,"preload_size":327680,"preload_interval":60,"preload_min_step":5,"preload_max_step":10}
             */

            private Video video_1;

            public Video getVideo_1() {
                return video_1;
            }

            public void setVideo_1(Video video_1) {
                this.video_1 = video_1;
            }
        }
    }
}
