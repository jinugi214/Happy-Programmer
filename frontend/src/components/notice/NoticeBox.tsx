import { Link } from "react-router-dom";

type NoticeBoxProps = {
  imgName: string;
  title: string;
  date: string;
  link: string;
};

function NoticeBox({ imgName, title, date, link }: NoticeBoxProps) {
  const imgUrl = process.env.PUBLIC_URL + "/imgs/" + imgName + ".png";

  return (
    <Link to={link}>
      <div className="w-64">
        <img src={imgUrl} alt={title} className="h-48"/>
        <div className="bg-bgbox p-4">
          <div className="text-s mb-4">{title}</div>
          <div>{date}</div>
        </div>
      </div>
    </Link>
  );
}

export default NoticeBox;
